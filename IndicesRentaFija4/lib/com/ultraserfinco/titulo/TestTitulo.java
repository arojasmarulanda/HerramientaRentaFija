/*    */ package com.ultraserfinco.titulo;
/*    */ 
/*    */ import java.time.LocalDate;
/*    */ import java.time.format.DateTimeFormatter;
/*    */ import java.time.temporal.ChronoUnit;
/*    */ import org.junit.Assert;
/*    */ import org.junit.Before;
/*    */ import org.junit.Test;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestTitulo
/*    */ {
/*    */   private Titulo titulo1;
/*    */   private Titulo tituloVencido;
/*    */   
/*    */   @Before
/*    */   public void setUp() {
/* 20 */     this.titulo1 = new Titulo("CDTBGA95V", "15NM20140410", EnumerableProperties.Estado.A, LocalDate.parse("20140121", DateTimeFormatter.BASIC_ISO_DATE), 
/* 21 */         LocalDate.parse("20310121", DateTimeFormatter.BASIC_ISO_DATE), EnumerableProperties.Periodicidad.T, EnumerableProperties.Modalidad.V, EnumerableProperties.Moneda.COP, EnumerableProperties.TipoTasa.getTipoTasa(3), 
/* 22 */         EnumerableProperties.TasaReferencia.IPC, "50000", EnumerableProperties.TipoCalculo.getTipoCalculo(2), EnumerableProperties.BaseCalculo.getBaseCalculo(0), "111.163", "110.434", 
/* 23 */         LocalDate.parse("20170822", DateTimeFormatter.BASIC_ISO_DATE), "7286.000", "842.580", "785.360", "000.00000", "000.728000", "375.820", "AAA", 3);
/* 24 */     this.tituloVencido = new Titulo("CDTBGA95V", "15NM20140410", EnumerableProperties.Estado.A, LocalDate.parse("20140121", DateTimeFormatter.BASIC_ISO_DATE), 
/* 25 */         LocalDate.parse("20170824", DateTimeFormatter.BASIC_ISO_DATE), EnumerableProperties.Periodicidad.T, EnumerableProperties.Modalidad.V, EnumerableProperties.Moneda.COP, EnumerableProperties.TipoTasa.getTipoTasa(3), 
/* 26 */         EnumerableProperties.TasaReferencia.IPC, "50000", EnumerableProperties.TipoCalculo.getTipoCalculo(2), EnumerableProperties.BaseCalculo.getBaseCalculo(0), "111.163", "110.434", 
/* 27 */         LocalDate.parse("20170822", DateTimeFormatter.BASIC_ISO_DATE), "7286.000", "842.580", "785.360", "000.00000", "000.728000", "375.820", "AAA", 3);
/*    */   }
/*    */ 
/*    */   
/*    */   @Test
/*    */   public void testGetDiasAlVencimiento() {
/* 33 */     Assert.assertEquals(this.titulo1.getDiasAlVencimiento(LocalDate.parse("20170822", DateTimeFormatter.BASIC_ISO_DATE)), ChronoUnit.DAYS.between(this.titulo1.getUltimaActualizacion(), this.titulo1.getFechaVencimiento()));
/*    */   }
/*    */   
/*    */   @Test
/*    */   public void testGetUltimoPrecioCalculado() {
/* 38 */     Assert.assertEquals(111.163D, this.titulo1.getUltimoPrecioCalculado(), 0.001D);
/* 39 */     Assert.assertEquals(111.163D, this.tituloVencido.getUltimoPrecioCalculado(), 0.001D);
/*    */   }
/*    */   
/*    */   @Test
/*    */   public void testGetTituloFromLine() {
/* 44 */     Titulo tituloFromLine = SXInfoValmerFormat.getTituloFromLine("00028DCDTBGA95V   15NM20140410000000000000000000A201708222014012120310121TV4897COP 3IPC +000000005.000000020000000000000111.163        000000000000000.000            000000000000110.434            +007.286000+008.42580+007.85360+000.00000000.728000+003.75820AAA       ", 
/* 45 */         3);
/* 46 */     Assert.assertEquals(this.titulo1, tituloFromLine);
/* 47 */     Assert.assertEquals(tituloFromLine.getDiasAlVencimiento(LocalDate.of(2017, 8, 22)), ChronoUnit.DAYS.between(tituloFromLine.getUltimaActualizacion(), tituloFromLine.getFechaVencimiento()));
/* 48 */     Assert.assertEquals(111.163D, tituloFromLine.getUltimoPrecioCalculado(), 0.001D);
/* 49 */     Assert.assertEquals(EnumerableProperties.Estado.A, tituloFromLine.getEstado());
/* 50 */     Assert.assertEquals("2014-01-21", tituloFromLine.getFechaEmision().toString());
/* 51 */     Assert.assertEquals("2031-01-21", tituloFromLine.getFechaVencimiento().toString());
/* 52 */     Assert.assertEquals("2017-08-22", tituloFromLine.getUltimaActualizacion().toString());
/* 53 */     Assert.assertEquals("15NM20140410", tituloFromLine.getIsin());
/* 54 */     Assert.assertEquals("CDTBGA95V", tituloFromLine.getNemo());
/* 55 */     Assert.assertEquals(EnumerableProperties.TasaReferencia.IPC, tituloFromLine.getTasaReferencia());
/*    */   }
/*    */   
/*    */   @Test
/*    */   public void testActualizarTitulo() {
/* 60 */     Titulo tituloFromLine = SXInfoValmerFormat.getTituloFromLine("38667DCDTCLP80    ISINCDCLP80 000000000000000000A201708242017060220190602MV0647COP 3IB1 +000000001.200000020000000000000100.289        000000000000000.000            000000000000099.895            +006.542000+001.67540+001.57250+000.00000000.395000+001.26590AAA       ", 
/* 61 */         3);
/* 62 */     Assert.assertEquals(tituloFromLine.getDiasAlVencimiento(LocalDate.of(2017, 8, 24)), ChronoUnit.DAYS.between(tituloFromLine.getUltimaActualizacion(), tituloFromLine.getFechaVencimiento()));
/* 63 */     Assert.assertEquals(100.289D, tituloFromLine.getUltimoPrecioCalculado(), 0.001D);
/* 64 */     Assert.assertEquals(EnumerableProperties.Estado.A, tituloFromLine.getEstado());
/* 65 */     Assert.assertEquals("2017-06-02", tituloFromLine.getFechaEmision().toString());
/* 66 */     Assert.assertEquals("2019-06-02", tituloFromLine.getFechaVencimiento().toString());
/* 67 */     Assert.assertEquals("2017-08-24", tituloFromLine.getUltimaActualizacion().toString());
/* 68 */     Assert.assertEquals("ISINCDCLP80", tituloFromLine.getIsin());
/* 69 */     Assert.assertEquals("CDTCLP80", tituloFromLine.getNemo());
/* 70 */     Assert.assertEquals(EnumerableProperties.TasaReferencia.IB1, tituloFromLine.getTasaReferencia());
/* 71 */     tituloFromLine.actualizarTitulo("38697DCDTCLP80    ISINCDCLP80 000000000000000000A201708252017060220190602MV0646COP 3IB1 +000000001.200000020000000000000100.309        000000000000000.000            000000000000099.896            +006.533000+001.67280+001.57020+000.00000000.413000+001.26410AAA       ");
/*    */     
/* 73 */     Assert.assertEquals(tituloFromLine.getDiasAlVencimiento(LocalDate.of(2017, 8, 25)), ChronoUnit.DAYS.between(tituloFromLine.getUltimaActualizacion(), tituloFromLine.getFechaVencimiento()));
/* 74 */     Assert.assertEquals(100.309D, tituloFromLine.getUltimoPrecioCalculado(), 0.001D);
/* 75 */     Assert.assertEquals(EnumerableProperties.Estado.A, tituloFromLine.getEstado());
/* 76 */     Assert.assertEquals("2017-06-02", tituloFromLine.getFechaEmision().toString());
/* 77 */     Assert.assertEquals("2019-06-02", tituloFromLine.getFechaVencimiento().toString());
/* 78 */     Assert.assertEquals("2017-08-25", tituloFromLine.getUltimaActualizacion().toString());
/* 79 */     Assert.assertEquals("ISINCDCLP80", tituloFromLine.getIsin());
/* 80 */     Assert.assertEquals("CDTCLP80", tituloFromLine.getNemo());
/* 81 */     Assert.assertEquals(EnumerableProperties.TasaReferencia.IB1, tituloFromLine.getTasaReferencia());
/*    */   }
/*    */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\titulo\TestTitulo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */