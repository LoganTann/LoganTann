#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define P sem_wait
#define V sem_post

#define NB_OPERATIONS 5
int v_globale = 5;

sem_t mot1, mot2;

void* thread_je_mes(void* args) {
    printf("Je ");
    V(&mot1);  // 👇 -
    P(&mot2);  // v  - 👌 -
    printf("mes ");
    V(&mot1);  // v  - ^  - 👇
}

void* thread_synchronise_threads(void* args) {
    P(&mot1);  // 👌 - ^  - v
    printf("synchronize ");
    V(&mot2);  // .. - ☝ - v
    P(&mot1);  // .. - .. - 👌
    printf("threads ");
}

int main(int argc, char const* argv[]) {
    pthread_t idThread1, idThread2;

    sem_init(&mot1, 0, 0);
    sem_init(&mot2, 0, 0);
    pthread_create(&idThread1, NULL, thread_je_mes, NULL);
    pthread_create(&idThread2, NULL, thread_synchronise_threads, NULL);
    pthread_join(idThread1, NULL);
    pthread_join(idThread2, NULL);
    printf("\nTous les threads fils sont terminés\n");
    sem_destroy(&mot1);
    sem_destroy(&mot2);
    return 0;
}