#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define P sem_wait
#define V sem_post

sem_t sema;

void* thread(void* args) {
    printf("J'attends...\n");
    sem_wait(&sema);
    printf("J'attends plus !\n");
}

int main(int argc, char const* argv[]) {
    pthread_t idThread;
    sem_init(&sema, 0, 0);
    pthread_create(&idThread, NULL, thread, NULL);

    for (int i = 1; i <= 3; i++) {
        sleep(1);
        printf("\tzzz (%d)\n", i);
    }
    sem_post(&sema);

    pthread_join(idThread, NULL);
    sem_destroy(&sema);
    return 0;
}