package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoOikeinAlussa() {
        assertEquals("saldo: 10.0", kortti.toString());      
    }
    
    
     @Test
    public void saldoOikein() {
        assertEquals(1000, kortti.saldo());      
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(500);
        assertEquals("saldo: 15.0", kortti.toString());
    }
    
    @Test
    public void rahanOttoToimiiJosRahaaTarpeeksi() {
        kortti.otaRahaa(100);
        assertEquals("saldo: 9.0", kortti.toString());
        assertTrue(kortti.otaRahaa(1));
    }
    
     @Test
    public void saldoEiMuutuJosRahaaEoOleTarpeeksi() {
        kortti.otaRahaa(1100);
        assertEquals("saldo: 10.0", kortti.toString());
        assertFalse(kortti.otaRahaa(1100));
    }
}
