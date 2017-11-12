#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <sys/socket.h>

/* Programme C qui envoie un datagramme UDP */
/* Author Djebien Tarik */

int 
main
(int argc, char* argv[]){
  if(argc==4){
        
    /* On recupère les données */
    char* ip = argv[1];
    int portEcoute = atoi(argv[2]);
    char* message = argv[3];
    
    /* Adresse reseau */
    struct sockaddr_in ad;

    /*Creation de la socket pour la connexion*/
    /*AF_INET pour le protocole IPV4 */
    /*SOCK_DGRAM pour un datagramme UDP, car c'est une socket utilisant le protocole UDP/IP en mode non connecté */
    /*on emploie un numéro de port nul afin de demander au noyau de nous en attribuer un libre.*/
    int maSocket = socket(AF_INET,SOCK_DGRAM,0);
        
    /*Definition de l'adresse reseau */
    ad.sin_family = AF_INET;
    ad.sin_port = htons(portEcoute);
    ad.sin_addr.s_addr = inet_addr(ip);
    /* Mettre à zéro tout le contenu de l’adresse */
    memset(&(ad.sin_zero), 0, sizeof (struct sockaddr_in));

    /*Envoi du message */
    
    /*int sendto (int sock, char * buffer, int taille_buffer, int attributs,struct sockaddr * source, socklen_t taille);*/
    sendto(maSocket,message,strlen(message),0,(struct sockaddr*) &ad, sizeof(struct sockaddr_in));
  }
  else{
    printf("usage : $ ./envoiUDP <AdresseIP> <PortEcoute> <Message à envoyer> \n");
    exit(EXIT_FAILURE);
  }
  exit(EXIT_SUCCESS);
}
