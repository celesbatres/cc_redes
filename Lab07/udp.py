import socket
import struct 

srcIPString = "127.0.0.1"
srcIP = socket.inet_aton(srcIPString)
srcPort = 9098
quit = False

#checksum

def checksum(msg):
    msgB = msg.encode('utf-8')
    
    length = len(msg)
    num_shorts = length // 2
    suma = 0
    
    # Sumar shorts (16 bits)
    shorts = struct.unpack(f'!{num_shorts}H', msgB[:num_shorts * 2])
    # print(type(shorts))
    # suma = sum(shorts)

    # shorts = struct.unpack('!' + 'H' * num_shorts, msg)
    
    # Sumar cada valor desempaquetado
    for val in shorts:
        suma += val
    
    # Si la longitud es impar, sumar el último byte como 8 bits
    if length % 2:
        suma += struct.unpack('!B', msgB[-1:])[0]
    
    # Ajustar carry
    suma = (suma >> 16) + (suma & 0xffff)
    
    # Complemento a uno
    checksum_value = ~((suma >> 16) + suma) & 0xffff
    return checksum_value


#headers
def headers(data, dstIPString, dstPort):
    ip_version = 4
    ip_ihl = 5
    ip_ToS = 0x00  #  '00000000'
    ip_TotalLength = ip_ihl + 20
    ip_Frag_ID = 0
    ip_Frag_Flags = 0b000  # '000'
    ip_Frag_Offset = 0  # '0000000000000'
    ip_TTL = 0xF
    ip_Protocol = 17
    ip_checksum = checksum(data)
    ip_header = 0
    # Mensaje a enviar en UDP < Es el parametro de la funcion (data)

    udp_checksum = checksum(data)

    udp_header = 0
    # loadData = struct.pack(f"{len(data)}s", data)
    loadData = struct.pack(f'{len(data)}s', data.encode('utf-8'))
    # dstIP = socket.inet_aton(dstIPString)
    udp_src_port = srcPort
    udp_dst_port = dstPort
    udp_lenght = 8 + len(loadData)
    # udp_checksum = 0

    srcIP_bin = srcIPString.encode('utf-8')
    dstIP_bin = dstIPString.encode('utf-8')

    #packing
    ip_header = struct.pack('!BBHHHBBH4s4s', (ip_version << 4) + ip_ihl, ip_ToS, 
    ip_TotalLength,
    ip_Frag_ID,
    (ip_Frag_Flags << 13) + ip_Frag_Offset, 
    ip_TTL,
    ip_Protocol,
    ip_checksum,
    srcIP_bin,
    dstIP_bin)
    udp_header = struct.pack("!H H H H", srcPort, dstPort, udp_lenght, udp_checksum)
    return ip_header + udp_header + loadData

while not(quit):
    text = input("Ingrese el texto a transmitir: ")
    if(text == "EXIT"):
        quit = True
        continue

    dstIPString = input("Ingrese un IP destino: ")
    dstIP = socket.inet_aton(dstIPString)

    dstPort = int(input("Ingrese un puerto destino: "))
    print ("message:", text) 
    
    packet = headers(text, dstIPString, dstPort)
    listTestByte = list(packet)
    print(listTestByte) 

    sock = socket.socket(socket.AF_INET, socket.SOCK_RAW, 0)
    # sock.setsockopt(socket.IPPROTO_IP, socket.IP_HDRINCL, 1)
    sock.connect((dstIPString, dstPort))
    sock.send(packet)
    # sock.sendto(packet, (dstIPString, 0))

    # sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, 0) # UDP
    # # destination = struct.pack('!H H 4s 8x', socket.AF_INET, dstPort, socket.inet_aton(dstIP))
    # sock.connect((dstIPString, dstPort))
    # sock.send(packet)
    # data, server = sock.recvfrom(4096)  # Recibir un máximo de 4096 bytes
    # print(f'Recibido: {data}')