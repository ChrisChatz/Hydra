# Hydra

## Athens University of Economics and Business 
## Msc in Computer Science 
### Distributed Systems Fall Semester 2016-2017

The project can be analyzed by the following discrete points:

1. Master
2. Workers
3. Clients (we don't care about it in the first deliverable, except from the fact that it should run in a dummy PC, sending messages to the master)


* **network package**:

	* **ServerSocketListener**:
		* Constructor: `public ServerSocketListener(int port, NetworkController parent)`
		
		  Has as arguments the port of the the connection and a NetworkController Object (we will explain later)
			
		* Run: `public void run()`
		
		  With that method, we add every socket connection in a HashMap in the NetworkController Class(method addSocket())
		
		* Open: `public void open()`
		
		  Creates a server socket

		* Close: `public void close()`
		
		  Closes the server socket

		
		Important point: Creates a socket connection which is open for every client.
		
	* **SocketListener**:
	
		* Constructor: `public SocketListener(Socket socket, NetworkController parent)`
		
		  Has as arguments a Socket Object and a NetworkController Object (like ServerSocketListener) and creates a 				  DataInputStream with that Socket Object
	
		* Run: `public void run()`
		
		  Takes messages from Clients and Workers. Creates a byte array with length the length of the message and then transform 		   it in a String. And finally sends the message with a unique key to NetworkController

		Important point: We have a SocketListener for each connection that we have.
		
	* **Network Controller**:
	
		* Constructor: `public NetworkController(int port , String role)`
		
		  Starts the method startServerSocketListener. Creates 2 hashMaps: 
		  1. `HashMap<String, Socket>socketmap;` and 
		  2.  `HashMap<String, SocketListener> slmap;`
	
		* startServerSocketListener: `public void startServerSocketListener(int port)`
		
		  Opens a ServerSocketListener with port the port that NetworkController listens to and all the attributes of       			  NetworkController Class. And then starts it.
	
	 	* addsocket: `public void addSocket(Socket s)`
	       
	          Add a new socket in `socketmap`. Creates a new SocketListener and starts it. Finally,  add the new SocketListener in                     `slmap`.
	
		* getMessageFromSL: `public void getMessageFromSL(String message, String slkey)`
		
		  Takes the message from the SocketListener and its key and send it to the ApplicationController. 
	
		* sendRequest: `public void sendRequest(Request req,String connId)`
		
		  Takes a Request and send it to the appropriate Client or Worker.
	
	    Important point: NetworkController is responsible for every socket connection and for sending Requests in Clients and    		    Workers.
