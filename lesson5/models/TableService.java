package lesson5.models;

import lesson5.presenters.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class TableService implements Model {

    private Collection<Table> tables;

    @Override
    public Collection<Table> loadTables() {
        if (tables == null) {
            tables = new ArrayList<>();

            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
            tables.add(new Table());
        }

        return tables;
    }

    @Override
    public int reservationTable(Date reservationDate, int tableNo, String name) {
        for (Table table : tables) {
            if (table.getNo() == tableNo) {
                Reservation reservation = new Reservation(table, reservationDate, name);
                table.getReservations().add(reservation);
                return reservation.getId();
            }
        }
        throw new RuntimeException("Некорректный номер столика");
    }

    @Override
    public void removeReservationTable(int oldReservationNumb) {
        for (Table table: tables) {
            for (Reservation x : table.getReservations()) {
                if (x.getId() == oldReservationNumb){ 
                    table.getReservations().remove(x);
                    System.out.println("Номер заказа " + oldReservationNumb + " удачно удален из забраннированных записей");
                    break;
                }
                else throw new RuntimeException("Некорректный номер заказа");   
            }
        }
    }

}
