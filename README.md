# Hydra

Distributed Systems

To project diaxwrizetai sta eksis diakrita simeia
1)Master
2)Workers
3)Clients (den mas endiaferei gia to prwto paradoteo ektos apo to na dimiourgithei ena dummy mixanima
to opoio apla tha stelnei minimata sto master -> worker -> google api gia na parei ti diadromi pou thelei)

1)paketo network:
	Server Socket listener:
		Constructor:
			Dexetai ws vasika orismata port kai ena antikeimeno tou network manager (tha eksigithei parakatw)
		
		Run:
			Apo ti stigmi pou kanei extend tin klasi thread exoume mia methodo run , stin opoia
			dexomaste socket connection apo clients kai apothikeuoume ayti ti sundesi se mia domi tou network controller (Hash map)
		Open:
			dimourgei to server socket
		Close :
		kleinei to server socket