/*    */ package com.ultraserfinco.titulo;
/*    */ 
/*    */ import org.junit.Assert;
/*    */ import org.junit.Test;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestFormatos
/*    */ {
/*    */   @Test
/*    */   public void testSXInfovalmerFormatGetters() {
/* 18 */     String line = "00119DCDTBBO80    COB01CD038E6000000000000000000A201708252016030420170904MV0010COP 3IB1 +000000002.300000020000000000000100.474        000000000000000.000            000000000000100.034            +005.772000+000.02740+000.02590+000.00000000.440000+000.54060F1+";
/*    */     
/* 20 */     Assert.assertEquals(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.ISIN), "COB01CD038E6");
/*    */   }
/*    */ 
/*    */   
/*    */   @Test
/*    */   public void testSMInfovalmerFormatGetters() {
/* 26 */     String line = "05038DCDTCOMS5V               A20170827                00060010022  +000.18340            S                  0000000070            032";
/* 27 */     Assert.assertEquals(SMInfoValmerFormat.getInfoFromLine(line, SMInfoValmerFormat.SMIndexes.NEMO), "CDTCOMS5V");
/* 28 */     Assert.assertEquals(Integer.parseInt(SMInfoValmerFormat.getInfoFromLine(line, SMInfoValmerFormat.SMIndexes.MONEDA)), 1L);
/* 29 */     Assert.assertEquals(Integer.parseInt(SMInfoValmerFormat.getInfoFromLine(line, SMInfoValmerFormat.SMIndexes.TASA)), 2L);
/* 30 */     Assert.assertEquals(Integer.parseInt(SMInfoValmerFormat.getInfoFromLine(line, SMInfoValmerFormat.SMIndexes.CALIF)), 70L);
/* 31 */     Assert.assertEquals(Integer.parseInt(SMInfoValmerFormat.getInfoFromLine(line, SMInfoValmerFormat.SMIndexes.CLASE)), 32L);
/*    */   }
/*    */   
/*    */   @Test
/*    */   public void testGetCodigoDiasAlVencimiento() {
/* 36 */     Assert.assertEquals(15L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(18));
/* 37 */     Assert.assertEquals(15L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(30));
/* 38 */     Assert.assertEquals(16L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(32));
/* 39 */     Assert.assertEquals(17L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(80));
/* 40 */     Assert.assertEquals(18L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(107));
/* 41 */     Assert.assertEquals(19L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(199));
/* 42 */     Assert.assertEquals(20L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(300));
/* 43 */     Assert.assertEquals(21L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(366));
/* 44 */     Assert.assertEquals(22L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(590));
/* 45 */     Assert.assertEquals(4L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(731));
/* 46 */     Assert.assertEquals(5L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(1345));
/* 47 */     Assert.assertEquals(7L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(2000));
/* 48 */     Assert.assertEquals(9L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(2834));
/* 49 */     Assert.assertEquals(11L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(3286));
/* 50 */     Assert.assertEquals(12L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(3955));
/* 51 */     Assert.assertEquals(13L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(4200));
/* 52 */     Assert.assertEquals(23L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(5010));
/* 53 */     Assert.assertEquals(24L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(5790));
/* 54 */     Assert.assertEquals(25L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(6570));
/* 55 */     Assert.assertEquals(26L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(6800));
/* 56 */     Assert.assertEquals(27L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(10000));
/* 57 */     Assert.assertEquals(27L, SMInfoValmerFormat.getCodigoDiasAlVencimiento(100000));
/*    */   }
/*    */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\titulo\TestFormatos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */