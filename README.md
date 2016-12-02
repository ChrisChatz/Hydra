# Hydra
Athens University of Economics and Business
Msc in Computer Science
Distributed Systems Fall Semester 2016-2017

The project can be analyzed by the following discrete points:
1)Master
2)Workers
3)Clients (we don't care about it in the first deliverable, except from the fact that it should run in a dummy PC, sending messages to the master)

1)
network package:

	Server Socket Listener:
		Constructor: public ServerSocketListener(int port, NetworkController parent)
			Has as arguments the port of the the connection and a NetworkController Object (we will 			explain later)
			
		Run: public void run()
			With that method, we add every socket connection in a HashMap in the 			NetworkController Class(method addSocket())
		
		Open: public void open()
			Creates a server socket

		Close: public void close()
			Closes the server socket

		
		Important point : Dimiourgia mias sundesis socket anoixtis gia olous tous clients.
		
Socket listener:
	
Constructor: 
	Dexetai ws basika orismata ena socket antikeimeno kai ena antikeimeno tou Network controller (opws kai i Server socket listener)
	kai dimiourgei ena input Datastream
	
Run:
	Perimenei message apo enan client. Dimiourgei enan pinaka apo bytes me to length tou minimatos wste na to prowthisei argotera
	se ayti tin morfi. Epeita dimiourgei ena monadiko kai meta stelnei to minima ston Network controller.

Point of the class:
	To noima tis sugkekrimenis klashs einai na ginontai spawn SL gia kathe connection pou erxetai.
		
Network Controller:
	
	Constructor: ksekina kai trexei tin sunartisi startServerSocketListener i opoia anoigei ena Server Socket Listener
	me to port kai me ola ta stoixeia tou antikeimenou tou Network Controller (ton anoigei kai ton ksekina)
	Ftiaxnei 2 hashMap (dictionaries) sto socketmap krataei kanei mapping ta keys me ta sockets. Sto slmap kanei mapping
	ena socket listener me kapoio string (?!)
	
	startServerSocketListener: eksigithike parapanw
	
	addsocket: Prosthetei neo socket sto hasmap dimiourgei enan neo socket listener kai ksekinaei na ton trexei
	telos prosthetei stin slmap ton neo socket listener.
	
	getMessageFromSL: pernei to req to metatrepei se string kai to ektypwnei.
	
	sendRequest: pernei to minima vriskei to socket pou tha to steilei , anoigei stream kai ksekinaei na to stelnei.
	
	main : dimiourgei enan neo Network controller se mia orismeni thyra pou exoume prokathorisei
	
Point of the Class: H dimiourgia enos antikeimenou pou arxika tha krataei se domes hashmap ta keys kai ta sockets . Opws
episis kai ta listeners pou einai upeuthina gia kathe connection. Entelei dimiourgei stream kai stelnei to request (apo opoion
kai na proerxetai) se paralipti ston opoio tha orisoume emeis. Me liga logia kanei tin antistixi douleia enos dromologiti
me ta arp tables tou.