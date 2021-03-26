
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate paate;
    Maksukortti kortti1;
    Maksukortti kortti2;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti1 = new Maksukortti(1000);
        kortti2 = new Maksukortti(10);
    }
    
    @Test
    public void saldoOikeinAlussa() {
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());    
    }
    
     @Test
    public void edullisenKateisostoToimiiKunRahaaOn() {
        assertEquals(10, paate.syoEdullisesti(250));
        assertEquals(100000+240, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
     @Test
    public void maukkaanKateisostoToimiiKunRahaaOn() {
        assertEquals(50, paate.syoMaukkaasti(450));
        assertEquals(100000+400, paate.kassassaRahaa());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
      @Test
    public void edullisenKateisostoToimiiKunRahatEiRiita() {
        assertEquals(200, paate.syoEdullisesti(200));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanKateisostoToimiiKunRahatEiRiita() {
        assertEquals(350, paate.syoMaukkaasti(350));
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
     @Test
    public void edullisenKorttiostoToimiiKunRahaaOn() {
        assertTrue(paate.syoEdullisesti(kortti1));
        //assertEquals(100000+240, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
    }
    
     @Test
    public void maukkaanKorttiostoToimiiKunRahaaOn() {
        assertTrue(paate.syoMaukkaasti(kortti1));
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
    }
    
      @Test
    public void edullisenKorttiostoToimiiKunRahatEiRiita() {
        assertFalse(paate.syoEdullisesti(kortti2));
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
    }
    
     @Test
    public void maukkaanKorttiostoToimiiKunRahatEiRiita() {
        assertFalse(paate.syoMaukkaasti(kortti2));
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void rahanLatausKortilleToimii() {
        paate.lataaRahaaKortille(kortti1, 10);
        assertEquals(1000+10, kortti1.saldo());
        assertEquals(100000+10, paate.kassassaRahaa());
    }
    
     @Test
    public void rahanLatausKortilleToimiiNegatiivisella() {
        paate.lataaRahaaKortille(kortti1, -10);
        assertEquals(1000, kortti1.saldo());
        assertEquals(100000, paate.kassassaRahaa());
    }
    
}
