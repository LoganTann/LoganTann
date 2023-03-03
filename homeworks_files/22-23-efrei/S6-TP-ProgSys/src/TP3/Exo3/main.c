#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define P sem_wait
#define V sem_post

sem_t barriere;
int nb_attentes = 0;
void barriere_register() { nb_attentes++; }
void barriere_end() {
    nb_attentes--;
    V(&barriere);
}
void* barriere_thread(void* args) {
    printf("[b_thread] Barrière atteinte, en attente...\n");
    do {
        P(&barriere);
    } while (nb_attentes > 0);
    printf("[b_thread] Je peux continuer !\n");
}

void* thread_dodo(void* args) {
    barriere_register();
    int* nbSleep = (int*)args;
    printf("[thread %d] démarré\n", *nbSleep);
    sleep(*nbSleep);
    printf("[thread %d] Point atteint\n", *nbSleep);
    barriere_end();
}

int main(int argc, char const* argv[]) {
    pthread_t idThread1, idThread2, idThread3, idThreadB;
    int arg1 = 1, arg2 = 2, arg3 = 3;

    sem_init(&barriere, 0, 0);
    pthread_create(&idThread1, NULL, thread_dodo, (void*)&arg1);
    pthread_create(&idThreadB, NULL, barriere_thread, NULL);
    pthread_create(&idThread2, NULL, thread_dodo, (void*)&arg2);
    pthread_create(&idThread3, NULL, thread_dodo, (void*)&arg3);
    pthread_join(idThread1, NULL);
    pthread_join(idThread2, NULL);
    pthread_join(idThread3, NULL);
    pthread_join(idThreadB, NULL);
    printf("\nTous les threads fils sont terminés\n");
    sem_destroy(&barriere);
    return 0;
}