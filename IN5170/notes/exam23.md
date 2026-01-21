int freeSeats = n;
int customers = 0;
int working = 0;

sem enterWaiting = 1;
sem availableCustomer = 0;
sem shownOut = 0;

process Customer {
    while (true) {
        P(enterWaiting)
        customer += 1
        if (customer > freeSeats) {
            if (working == 0 and customer == freeSeats) {
                V(enterWaiting)
                V(availableCustomer)
                P(shownOut)
                customer -= 1;
            } else {
                customer -= 1;
                V(enterWaiting)
            }
        } else {
            V(enterWaiting)
            V(availableCustomer)
            P(shownOut)
            customer -= 1;
        }
    }
}


process Barber {
    while (true) {
        P(availableCustomer)
        working += 1
        # cut hair

        V(shownOut)
        working -= 1
    }
}






monitor Barbershop {
    int nr = 0; // number of regular customers
    int np = 0; // number of priority customers
    int waiting = 0;
    int barberReady = 0;
    int seats = n

    cond barberReady;
    cond customerReady;
}


procedure barber(){
    while (true) {
        while (wa)
    }
}


procedure regularCustomer() {
    while (true) {

    }
}


procedure priorityCustomer() {
    while (true) {

    }
}