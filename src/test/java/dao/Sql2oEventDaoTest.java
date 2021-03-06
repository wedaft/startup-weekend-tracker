package dao;

import models.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oEventDaoTest {

    private Sql2oEventDao eventDao;
    private Connection conn;
    
    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        eventDao = new Sql2oEventDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void Event_instantiatesCorrectly() {
        Event newEvent = newEvent();
        assertTrue(newEvent instanceof Event);
    }

    @Test
    public void getNameFromNewEvent_UtilizingDao () throws Exception {
        Event newEvent = newEvent();
        assertEquals("Utilizing DAO",newEvent.getName());
    }

    @Test
    public void getId_and_addEventingEventSetsId () throws Exception {
        Event newEvent = newEvent();
        int localEventId = newEvent.getId();
        eventDao.addEvent(newEvent);
        assertNotEquals(localEventId,newEvent.getId());
    }

    @Test
    public void findEventById() throws Exception {
        Event newEvent = newEvent();
        eventDao.addEvent(newEvent);
        int thisId = newEvent.getId();
        Event foundEvent = eventDao.findEventById(thisId);
        assertEquals(thisId, foundEvent.getId());
    }

    @Test
    public void returnListOfAllEvents() throws Exception {
        Event newEvent = newEvent();
        Event newEvent2 = new Event("2nd Event", "It's another event");
        eventDao.addEvent(newEvent);
        eventDao.addEvent(newEvent2);
        assertEquals(2, eventDao.getAllEvents().size());
    }

    @Test
    public void updateASingleEvent() throws Exception {
        Event newEvent = newEvent();
        String oldName = newEvent.getName();
        eventDao.addEvent(newEvent);
        Event foundEvent = eventDao.findEventById(newEvent.getId());
        eventDao.updateEventById("Test","It's all about the tests",4, foundEvent.getId());
        Event updatedEvent = eventDao.findEventById(foundEvent.getId());
        assertNotEquals(oldName, updatedEvent.getName());
    }

    @Test
    public void deleteById() throws Exception {
        Event newEvent = newEvent();
        eventDao.addEvent(newEvent);
        int idToDelete = newEvent.getId();
        eventDao.deleteEventById(idToDelete);
        assertEquals(0, eventDao.getAllEvents().size());
    }

    //helper
    public Event newEvent() {
        return new Event("Utilizing DAO", "How to use DAO");
    }
}