package lesson5.presenters;

import java.util.Date;

public interface ViewObserver {

    void onReservationTable(Date orderDate, int tableNo, String name);


    void onChangeReservationTable(int oldReservationNumb, Date newDate, int newTable, String newName);
}
