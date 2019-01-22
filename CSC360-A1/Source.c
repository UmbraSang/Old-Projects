//#include "Source.h"
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/*
 * List
 *
 *  Created on: Jan 10, 2019
 *      Author: Connor
 */

struct Node {
	int data;
	struct Node* next;
	struct Node* prev;
};

struct List {
	int length;
	struct Node* head;
	struct Node* tail;
};

int remove(int index, struct List* nodeList);
void add(int x, struct Node* newNode, struct List* nodeList);
void addLast(struct Node* newNode, struct List* nodeList);
int getLength(struct List* nodeList);
bool isEmpty(struct List* nodeList);
struct Node* makeNode(int data);
void adjSwap(struct Node* a, struct Node* b, struct Node* p, struct Node* n);
struct List* makeList();
int pop(struct List* nodeList);
void printList(struct List* nodeList);
void printListRev(struct List* nodeList);
void push(struct Node* newHead, struct List* nodeList);
int removeLast(struct List* nodeList);
void shiftLeft(struct List* list, int n);
void swap(struct List* nodeList, struct Node* ref1, struct Node* ref2);

void surgery(struct List* nodeList) {
	struct Node* curr = nodeList->head;
	while (curr->next != NULL) {
		if (curr->next->prev != curr) {
			curr->next->prev = curr;
		}
		curr = curr->next;
	}
}

int getLength(struct List* nodeList) {
	return nodeList->length;
}

bool isEmpty(struct List* nodeList) {
	if (getLength(nodeList)<=0) {
		return true;
	} else {
		return false;
	}
}

struct Node* makeNode(int data) {
	struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
	newNode->data = data;
	newNode->next = NULL;
	newNode->prev = NULL;
	return newNode;
}

struct List* makeList() {
	struct List* nodeList = (struct List*)malloc(sizeof(struct List));
	nodeList->length = 0;
	nodeList->head = NULL;
	nodeList->tail = NULL;
	return nodeList;
}

void push(struct Node* newHead, struct List* nodeList) {
	if (isEmpty(nodeList)) {
		nodeList->head = newHead;
		nodeList->tail = newHead;
	}
	else {
		newHead->next = nodeList->head;
		nodeList->head->prev = newHead;
		nodeList->head = newHead;
	}
	nodeList->length++;
}

void addLast(struct Node* newNode, struct List* nodeList) {
	if (isEmpty(nodeList)) {
		nodeList->head = newNode;
		nodeList->tail = newNode;
	}
	else {
		nodeList->tail->next = newNode;
		newNode->prev = nodeList->tail;
		nodeList->tail = newNode;
	}
	nodeList->length++;
}

void add(int x, struct Node* newNode, struct List* nodeList) {
	if (x == 0) {
		push(newNode, nodeList);
	}
	else if (x >= nodeList->length) {
		addLast(newNode, nodeList);
	}
	else {
		struct Node* curr = nodeList->head;
		for (int i = 0; i < x; i++) {
			curr = curr->next;
		}
		struct Node* currPrev = curr->prev;
		currPrev->next = newNode;
		newNode->prev = currPrev;
		newNode->next = curr;
		curr->prev = newNode;

		nodeList->length++;
	}
}

int pop(struct List* nodeList) {
	struct Node* oldHead = nodeList->head;
	if (getLength(nodeList) == 1) {
		nodeList->head = NULL;
		nodeList->tail = NULL;
	} else {
		nodeList->head = nodeList->head->next;
		nodeList->head->prev = NULL;
		oldHead->next = NULL;
	}
	nodeList->length--;
	int data = oldHead->data;
	free(oldHead);
	return data;
}

int removeLast(struct List* nodeList) {
	struct Node* oldTail = nodeList->tail;
	if (getLength(nodeList) == 1) {
		nodeList->head = NULL;
		nodeList->tail = NULL;
	}
	else {
		nodeList->tail = nodeList->tail->prev;
		nodeList->tail->next = NULL;
	}
	oldTail->prev = NULL;
	nodeList->length--;
	int data = oldTail->data;
	free(oldTail);
	return data;
}

int remove(int index, struct List* nodeList) {
	if (index == 0) {
		return pop(nodeList);
	}
	else if (index >= nodeList->length-1) {
		return removeLast(nodeList);
	}
	else {
		struct Node* curr = nodeList->head;
		int i;
		for (i = 0; i < index; i++) {
			curr = curr->next;
		}
		struct Node* currPrev = curr->prev;
		struct Node* currNext = curr->next;
		currPrev->next = currNext;
		currNext->prev = currPrev;
		curr->next = NULL;
		curr->prev = NULL;

		nodeList->length--;
		int data = curr->data;
		free(curr);
		return data;
	}
}

void adjSwap(struct Node* a, struct Node* b, struct Node* p, struct Node* n) {
	if (p != NULL) {
		p->next = b;
	}
	b->next = a;
	a->next = n;
	if (n != NULL) {
		n->prev = a;
	}
	a->prev = b;
	b->prev = p;
}

void swap(struct List* nodeList, struct Node* ref1, struct Node* ref2) {
	struct Node* prev1 = ref1->prev;
	struct Node* next1 = ref1->next;
	struct Node* prev2 = ref2->prev;
	struct Node* next2 = ref2->next;

	
	if (next1 == ref2) {
		adjSwap(ref1, ref2, prev1, next2);
	}
	else if (next2 == ref1) {
		adjSwap(ref2, ref1, prev2, next1);
	}
	else {
		ref1->next = next2;
		ref1->prev = prev2;
		ref2->next = next1;
		ref2->prev = prev1;
		prev1->next = ref2;
		prev2->next = ref1;
		next1->prev = ref2;
		next2->prev = ref1;
	}
	surgery(nodeList);
}

void shiftLeft(struct List* list, int n) {
	if (getLength(list)<=n) {
		n = getLength(list);
	}
	int i;
	for (i = 0; i < n; i++) {
		pop(list);
	}
	//list->head->prev = NULL;
}

struct List* reverse(struct List* nodeList) {
	struct List* reversed = makeList();
	int i;
	int length = getLength(nodeList);
	for (i = 0; i < length; i++) {
		push(makeNode(pop(nodeList)), reversed);
	}
	free(nodeList);
	return reversed;
}

struct List* concat(struct List* a, struct List* b) {
	if (isEmpty(a)) {
		free(a);
		return b;
	}
	if (isEmpty(b)) {
		free(b);
		return a;
	}
	a->tail->next = b->head;
	b->head->prev = a->tail;
	a->tail = b->tail;
	a->length = a->length + b->length;
	free(b);
	return a;
}

void unique(struct List* nodeList) {
	struct Node* curr = nodeList->head;
	struct Node* temp = curr->next;
	int i;
	int j;
	for (i = 0; curr!=NULL; i++) {
		for (j = 1; temp!=nodeList->tail->next; j++) {
			if (temp->data == curr->data) {
				remove(j + i, nodeList);
				temp = curr->next;
			}
			temp = temp->next;
		}

		curr = curr->next;
		if (curr != NULL) {
			temp = curr->next;
		}
	}
	//surgery(nodeList);
}

int distance(struct Node* ref1, struct Node* ref2) {
	if (ref1 == ref2) {
		return 0;
	}
	struct Node* curr = ref1->next;
	int i = 1;
	while (curr != NULL) {
		if (curr == ref2) {
			return i;
		}
		curr = curr->next;
		i++;
	}
	curr = ref2->next;
	i = 1;
	while (curr != NULL) {
		if (curr == ref1) {
			return i;
		}
		curr = curr->next;
		i++;
	}
	return -1;
}

void rotateLeft(struct List* nodeList, int n) {
	nodeList->tail->next = nodeList->head;
	nodeList->head->prev = nodeList->tail;
	int i;
	struct Node* curr = nodeList->head;
	for (i = 0; i < (n%getLength(nodeList)); i++) {
		nodeList->head = nodeList->head->next;
	}
	nodeList->tail = nodeList->head->prev;
	nodeList->head->prev = NULL;
	nodeList->tail->next = NULL;
}

void minMax(struct List* nodeList, int* min, int* max) {
	if (isEmpty(nodeList)) {
		return;
	}
	struct Node* curr = nodeList->head;
	int i;
	*min = curr->data;
	*max = curr->data;
	curr = curr->next;
	for (i = 1; curr!=NULL; i++) {
		if (curr->data < *min) {
			*min = curr->data;
		}
		if (curr->data > *max) {
			*max = curr->data;
		}
		curr = curr->next;
	}
}

bool includes(struct List* nodeList, struct Node* ref2) {
	if (isEmpty(nodeList) ){
		return false;
	}
	int i;
	struct Node* curr = nodeList->head;
	for (i = 0; curr!=NULL; i++) {
		if (curr == ref2) {
			return true;
		}
		curr = curr->next;
	}
	return false;
}

void emptyList(struct List* nodeList) {
	while(nodeList->head!=NULL){
		pop(nodeList);
	}
}

void printList(struct List* nodeList) {
	if (isEmpty(nodeList)) {
		printf("Empty List\n");
	}
	else {
		struct Node* curr = nodeList->head;
		while (curr!=NULL) {
			printf("%d\n", curr->data);
			curr = curr->next;
		}
	}
}

void printListRev(struct List* nodeList) {
	if (isEmpty(nodeList)) {
		printf("Empty List\n");
	}
	else {
		struct Node* curr = nodeList->tail;
		while (curr != NULL) {
			printf("%d\n", curr->data);
			curr = curr->prev;
		}
	}
}

//Test Suite:
void addingRemovingTests(struct List* nodeList) {
	printf("Basic Tests Start\n");
	printList(nodeList);
	printf("isEmpty: %d\n", isEmpty(nodeList));
	printf("List Length: %d\n", getLength(nodeList));
	printf("Empty Tests Finished\n\n");

	push(makeNode(7), nodeList);
	addLast(makeNode(2), nodeList);
	add(1, makeNode(4), nodeList);
	printList(nodeList);
	printf("Reversed:\n");
	printListRev(nodeList);
	printf("isEmpty: %d\n", isEmpty(nodeList));
	printf("List Length: %d\n", getLength(nodeList));
	printf("Adding Tests Finished\n\n");

	printf("%d\n", remove(1, nodeList));
	printf("remove done\n");
	printf("%d\n", removeLast(nodeList));
	printf("removeLast done\n");
	printf("%d\n", pop(nodeList));
	printf("pop done\n");
	printList(nodeList);
	printf("isEmpty: %d\n", isEmpty(nodeList));
	printf("List Length: %d\n", getLength(nodeList));
	printf("Removing Tests Finished\n\n");
}

void makeListForTests(struct List* nodeList) {
	add(5, makeNode(1), nodeList);
	add(5, makeNode(6), nodeList);
	add(5, makeNode(3), nodeList);
	add(5, makeNode(7), nodeList);
	add(5, makeNode(5), nodeList);
}

void swapsTests(struct List* nodeList) {
	printf("Swaps Test Start\n");
	makeListForTests(nodeList);
	printf("Pre-Swaps:\n");
	printList(nodeList);
	printf("Reversed:\n");
	printListRev(nodeList);

	printf("In Order:\n");
	struct Node* four = makeNode(4);
	add(3, four, nodeList);
	struct Node* two = makeNode(2);
	add(1, two, nodeList);
	swap(nodeList, four, two);
	printList(nodeList);
	printf("Reversed:\n");
	printListRev(nodeList);

	printf("adjSwap:\n");
	struct Node* three = makeNode(3);
	add(5, three, nodeList);
	swap(nodeList, two, three);
	printList(nodeList);
	printf("Reversed:\n");
	printListRev(nodeList);

	printf("Undo:\n");
	swap(nodeList, two, three);
	printList(nodeList);
	printf("Reversed:\n");
	printListRev(nodeList);

	printf("Swap Tests Finished\n\n");
}

struct List* assortedTests(struct List* nodeList) {
	makeListForTests(nodeList);
	nodeList = reverse(nodeList);
	printf("Reversed List:\n");
	printList(nodeList);

	struct List* second = makeList();
	add(5, makeNode(19), second);
	struct Node* four = makeNode(4);
	add(5, four, second);
	add(5, makeNode(19), second);
	add(5, makeNode(7), second);
	
	printf("List one:\n");
	printList(nodeList);
	printf("List two\n");
	printList(second);
	printf("Combined:\n");
	concat(nodeList, second);
	printList(nodeList);

	unique(nodeList);
	printf("Removed Doubles:\n");
	printList(nodeList);
	printf("reversing, combining, and uniqueness Tests complete\n\n");

	printf("Distance Test\n");
	printList(nodeList);
	printf("Distance from head to tail: %d\n", distance(nodeList->head, nodeList->tail));
	rotateLeft(nodeList, 5);
	printf("rotated by 5\n");
	printList(nodeList);

	int min;
	int max;
	minMax(nodeList, &min, &max);
	printf("NodeList Min: %d, Max: %d\n", min, max);

	if (includes(nodeList, four)) {
		printf("nodeList contains 4\n");
	}
	else {
		printf("nodeList does not contain 4\n");
	}
	printf("Distance, Rotation, Minmax, and Includes Tests Finished\n\n");
	return nodeList;
}

void shiftTests(struct List* nodeList) {
	printf("Shift Tests Start\n");
	//makeListForTests(nodeList);
	printList(nodeList);
	shiftLeft(nodeList, 1);
	printf("Shift 1:\n");
	printList(nodeList);
	printf("Reversed:\n");
	printListRev(nodeList);

	shiftLeft(nodeList, 3);
	printf("Shift 3:\n");
	printList(nodeList);
	printf("Reversed:\n");
	printListRev(nodeList);

	shiftLeft(nodeList, 10);
	printf("Shift More Than List Length:\n");
	printList(nodeList);
	printf("Shift Tests Finished\n\n");
}

int main() {

	struct List* nodeList = makeList();
	printf("Starting Test Suite\n\n");
	addingRemovingTests(nodeList);
	swapsTests(nodeList);
	shiftTests(nodeList);
	nodeList = assortedTests(nodeList);
	emptyList(nodeList);
	printf("list emptied\n");
	printList(nodeList);
	printf("\nFinished Test Suite\n\n");
}
