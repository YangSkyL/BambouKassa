package be.linyang.kassa.Model.ticket;

import be.linyang.kassa.Model.TicketItem;
import be.linyang.kassa.Model.items.Item;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity("tickets")
public class Ticket {

    @Id
    private ObjectId id;

    private String ticketNr;

    private String name;

    private String date;
    private String time;

    private Status status;

    @Reference
    private List<TicketItem> items;


    private String tableNr;

    public Ticket()
    {
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().toString();
        this.status = Status.ACTIVE;
    }

    public Ticket(String date, List<TicketItem> items) {
        this();
        this.date = date;
        this.items = items;
        this.name = "";
    }

    public Ticket(String date, List<TicketItem> items, String name) {
        this();
        this.date = date;
        this.items = items;
        this.name = name;
    }

    public Ticket(String ticketNr, String name, List<TicketItem> items, String tableNr) {
        this();
        this.ticketNr = ticketNr;
        this.name = name;
        this.items = items;
        this.tableNr = tableNr;
    }

    public ObjectId getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public List<TicketItem> getItems() {
        return items;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setItems(List<TicketItem> items) {
        this.items = items;
    }

    public String getTicketNr() {
        return ticketNr;
    }

    public String getTime() {
        return time;
    }

    public void setTicketNr(int ticketNr) {
        this.ticketNr = this.date + "-" + ticketNr;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTable() {
        return tableNr;
    }

    public void setTableNr(String tableNr) {
        this.tableNr = tableNr;
    }

    public double getTotalPrice()
    {
        return this.items.stream()
                .mapToDouble(TicketItem::getTotalPrice)
                .sum();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTableNr() {
        return tableNr;
    }

    public void addItemToTicket(TicketItem ticketItem) {
        this.items.add(ticketItem);
    }

    public enum Status{
        ACTIVE,PAID;
    }
}
