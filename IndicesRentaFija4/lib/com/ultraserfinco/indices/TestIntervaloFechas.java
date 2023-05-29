/*    */ package com.ultraserfinco.indices;
/*    */ 
/*    */ import java.time.LocalDate;
/*    */ import org.junit.Assert;
/*    */ import org.junit.Test;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestIntervaloFechas
/*    */ {
/*    */   @Test
/*    */   public void testContieneFechaEIntervalo() {
/* 13 */     LocalDate fecha1 = LocalDate.of(2017, 2, 23);
/* 14 */     LocalDate fecha2 = LocalDate.of(2017, 3, 30);
/* 15 */     LocalDate fecha3 = LocalDate.of(2017, 2, 24);
/* 16 */     LocalDate fecha4 = LocalDate.of(2017, 2, 27);
/*    */     
/* 18 */     IntervaloFechas intervalo1 = new IntervaloFechas(fecha1, fecha2);
/* 19 */     IntervaloFechas intervalo2 = new IntervaloFechas(fecha3, fecha4);
/* 20 */     Assert.assertTrue("Este intervalo contiene todas las fechas", (
/* 21 */         intervalo1.contieneFecha(fecha1) && intervalo1.contieneFecha(fecha2) && 
/* 22 */         intervalo1.contieneFecha(fecha3) && intervalo1.contieneFecha(fecha4)));
/* 23 */     Assert.assertTrue(intervalo2.contieneFecha(fecha3));
/* 24 */     Assert.assertFalse(intervalo2.contieneFecha(fecha1));
/*    */     
/* 26 */     Assert.assertTrue(intervalo1.contieneIntervalo(intervalo1));
/* 27 */     Assert.assertTrue(intervalo1.contieneIntervalo(intervalo2));
/* 28 */     Assert.assertFalse(intervalo2.contieneIntervalo(intervalo1));
/* 29 */     Assert.assertFalse((new IntervaloFechas(fecha1, fecha4))
/* 30 */         .contieneIntervalo(new IntervaloFechas(fecha3, fecha2)));
/*    */ 
/*    */     
/*    */     try {
/* 34 */       Assert.fail("Deberia lanzar una excepcion");
/* 35 */     } catch (IllegalArgumentException ex) {
/* 36 */       Assert.assertEquals(ex.getMessage(), "La fecha inicial no puede ser posterior a la final");
/*    */     } 
/*    */   }
/*    */   
/*    */   @Test
/*    */   public void testIntersectarCon() {
/* 42 */     LocalDate fecha1 = LocalDate.of(2017, 2, 23);
/* 43 */     LocalDate fecha2 = LocalDate.of(2017, 3, 30);
/* 44 */     LocalDate fecha3 = LocalDate.of(2017, 2, 24);
/* 45 */     LocalDate fecha4 = LocalDate.of(2017, 2, 27);
/*    */     
/* 47 */     IntervaloFechas intervalo1 = new IntervaloFechas(fecha1, fecha2);
/* 48 */     IntervaloFechas intervalo2 = new IntervaloFechas(fecha3, fecha4);
/* 49 */     IntervaloFechas intervalo3 = new IntervaloFechas(fecha1, fecha4);
/* 50 */     IntervaloFechas intervalo4 = new IntervaloFechas(fecha3, fecha2);
/* 51 */     Assert.assertEquals(intervalo1.intersectarCon(intervalo2), intervalo2);
/* 52 */     Assert.assertEquals(intervalo2.intersectarCon(intervalo1), intervalo2);
/* 53 */     Assert.assertEquals(intervalo3.intersectarCon(intervalo4), new IntervaloFechas(fecha3, fecha4));
/*    */   }
/*    */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\indices\TestIntervaloFechas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */