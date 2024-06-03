#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define NB_OPERATIONS 5
int v_globale = 5;

void* calcul_pere() {
    srand(1);
    for (int i = 0; i < NB_OPERATIONS; i++) {
        useconds_t random_sleep = rand() % 1000000;
        v_globale += 10;
        usleep(random_sleep);
        printf("[%4d] [%s]    i=%d, v_globale=%d\n",
               (int)(random_sleep * 0.001), "Pere", i, v_globale);
    }
}

void* calcul_thread(void* ptr) {
    srand(2);
    printf("Bonjour \n");
    for (int i = 0; i < NB_OPERATIONS; i++) {
        useconds_t random_sleep = rand() % 1000000;
        v_globale *= 2;
        usleep(random_sleep);
        printf("[%4d] [%s]    i=%d, v_globale=%d\n",
               (int)(random_sleep * 0.001), "Thrd", i, v_globale);
    }
    printf("Fils terminé\n");
}

int main(int argc, char const* argv[]) {
    pthread_t idThread;
    pthread_create(&idThread, NULL, calcul_thread, NULL);
    calcul_pere();
    printf("Père terminé\n");
    pthread_join(idThread, NULL);
    return 0;
}