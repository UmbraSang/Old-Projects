
#include <sys/types.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
//#include <unistd.h>
//#include <sys/wait.h>

#include "DoublyLinkedList.h"

#define TOKEN_BUFFER_SIZE 64

char* intakeLine() {
	char* line = NULL;
	size_t buffsize = 0;
	getline(&line, &buffsize, stdin);
	return line;
}

struct List* getTokens(char* input) {
	char* token = strtok(input, " \t\r\n\a");
	struct List* tokarr = makeList();
	while (token!=NULL){
		addLast(makeNode(token), tokarr);
		token = strtok(NULL, " \t\r\n\a");
	}
	return tokarr;
}


int kapex(char** args) {
	int i;
	for(i=0, i< ; i++){
		if(strcmp(args[0], )==0){

		}
	}
}


char** listToArray(struct List* list){
	char** arr = malloc(list->length * sizeof(char*));
	int i = 0;
	while(list->head){
		arr[i] = pop(list);
		i++;
	}
	return arr;
}

void testPrint(struct List* args){
	printList(args);
	printListRev(args);
}

void argsReader() {
	char* input;
	struct List* args;
	int status;

	do{
		printf("> ");
		input = intakeLine();
		args = getTokens(input);
		testPrint(args);
		status = kapex(listToArray(args));

		free(input);
		free(args);
	} while (status);
}
/*
int launch(char** args){
	pid_t pid, wpid;
	int status;

	pid = fork();
	if(pid==0){
		//child part
		if(execvp(args[0], args)==-1){
			perror("failed exec() call\n");
		}
		exit(EXIT_FAILURE);
	} else if (pid < 0){
		//error part
		perror("bad fork\n");
	} else{
		//parent part
		wpid = wait();
	}
	return 1;
}
*/
int main(int argc, char **argv) {
	// Load config files, if any.

	// Run command loop.
	argsReader();
	// Perform any shutdown/cleanup.

	return EXIT_SUCCESS;
}