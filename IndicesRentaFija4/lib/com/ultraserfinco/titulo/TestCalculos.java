/*    */ package com.ultraserfinco.titulo;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import java.math.RoundingMode;
/*    */ import java.time.LocalDate;
/*    */ import java.util.Set;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TestCalculos
/*    */ {
/*    */   public BaseDeDatosTitulos startBaseDeDatos() {
/* 24 */     BaseDeDatosTitulos baseDatos = new BaseDeDatosTitulos(3);
/* 25 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SP082217.002", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082217.002", LocalDate.of(2017, 22, 8));
/* 26 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082317.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082317.002", LocalDate.of(2017, 23, 8));
/* 27 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082417.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082417.002", LocalDate.of(2017, 24, 8));
/* 28 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082517.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082517.002", LocalDate.of(2017, 25, 8));
/* 29 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082617.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082617.003", LocalDate.of(2017, 26, 8));
/* 30 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082717.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082717.004", LocalDate.of(2017, 27, 8));
/* 31 */     return baseDatos;
/*    */   }
/*    */   
/*    */   @Test
/*    */   public void testCalculoRentabilidadConTitulos() {
/* 36 */     Titulo titulo1 = SXInfoValmerFormat.getTituloFromLine("00028DCDTBGA95V   15NM20140410000000000000000000A201708222014012120310121TV4897COP 3IPC +000000005.000000020000000000000111.163        000000000000000.000            000000000000110.434            +007.286000+008.42580+007.85360+000.00000000.728000+003.75820AAA       ", 
/* 37 */         3);
/* 38 */     titulo1.actualizarTitulo("00028DCDTBGA95V   15NM20140410000000000000000000A201708232014012120310121TV4897COP 3IPC +000000005.000000020000000000000112.163        000000000000000.000            000000000000110.434            +007.286000+008.42580+007.85360+000.00000000.728000+003.75820AAA       ");
/*    */     
/* 40 */     Assert.assertEquals(titulo1.getUltimoDatoTitulo(Titulo.DatosTitulo.RENTABILIDAD), 0.0089555777D, 1.0E-8D);
/* 41 */     titulo1.actualizarTitulo("00028DCDTBGA95V   15NM20140410000000000000000000A201708242014012120310121TV4897COP 3IPC +000000005.000000020000000000000110.434        000000000000000.000            000000000000110.540            +007.286000+008.42580+007.85360+000.00000000.728000+003.75820AAA       ");
/*    */     
/* 43 */     Assert.assertEquals("Se pago cupon y por tanto se considera la rentabilidad del dia anterior", titulo1.getUltimoDatoTitulo(Titulo.DatosTitulo.RENTABILIDAD), -0.0155351129879D, 1.0E-8D);
/*    */   }
/*    */   
/*    */   @Test
/*    */   public void testCalculosRentabilidad() throws ArgumentNotFoundException {
/* 48 */     BaseDeDatosTitulos baseDatos = new BaseDeDatosTitulos(3);
/* 49 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SP082217.002", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082217.002", LocalDate.of(2017, 8, 22));
/* 50 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082317.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082317.002", LocalDate.of(2017, 8, 23));
/* 51 */     int scale = 15;
/* 52 */     Set<Titulo> titulos = baseDatos.getTitulosFromInfo("NO_APLICA", 3, 70, 11, 5, EnumerableProperties.Moneda.COP);
/* 53 */     Assert.assertEquals(((BigDecimal)baseDatos.getValoresPromedioDeGrupo(titulos, scale, new Titulo.DatosTitulo[] { Titulo.DatosTitulo.RENTABILIDAD }).get(0)).toString(), (
/* 54 */         new BigDecimal("-0.000575158091905")).setScale(scale, RoundingMode.HALF_UP).toString());
/* 55 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082417.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082417.002", LocalDate.of(2017, 8, 24));
/* 56 */     titulos = baseDatos.getTitulosFromInfo("NO_APLICA", 3, 70, 11, 5, EnumerableProperties.Moneda.COP);
/* 57 */     Assert.assertEquals(((BigDecimal)baseDatos.getValoresPromedioDeGrupo(titulos, scale, new Titulo.DatosTitulo[] { Titulo.DatosTitulo.RENTABILIDAD }).get(0)).toString(), (
/* 58 */         new BigDecimal("0.000254889758221")).setScale(scale, RoundingMode.HALF_UP).toString());
/* 59 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082517.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082517.002", LocalDate.of(2017, 8, 25));
/* 60 */     titulos = baseDatos.getTitulosFromInfo("NO_APLICA", 3, 70, 11, 5, EnumerableProperties.Moneda.COP);
/* 61 */     Assert.assertEquals(((BigDecimal)baseDatos.getValoresPromedioDeGrupo(titulos, scale, new Titulo.DatosTitulo[] { Titulo.DatosTitulo.RENTABILIDAD }).get(0)).toString(), (
/* 62 */         new BigDecimal("-0.001344303670179")).setScale(scale, RoundingMode.HALF_UP).toString());
/* 63 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082617.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082617.003", LocalDate.of(2017, 8, 26));
/* 64 */     titulos = baseDatos.getTitulosFromInfo("NO_APLICA", 3, 70, 11, 5, EnumerableProperties.Moneda.COP);
/* 65 */     Assert.assertEquals(((BigDecimal)baseDatos.getValoresPromedioDeGrupo(titulos, scale, new Titulo.DatosTitulo[] { Titulo.DatosTitulo.RENTABILIDAD }).get(0)).toString(), (
/* 66 */         new BigDecimal("0.000185418151599")).setScale(scale, RoundingMode.HALF_UP).toString());
/* 67 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082717.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082717.004", LocalDate.of(2017, 8, 27));
/* 68 */     titulos = baseDatos.getTitulosFromInfo("NO_APLICA", 3, 70, 11, 5, EnumerableProperties.Moneda.COP);
/* 69 */     Assert.assertEquals(((BigDecimal)baseDatos.getValoresPromedioDeGrupo(titulos, scale, new Titulo.DatosTitulo[] { Titulo.DatosTitulo.RENTABILIDAD }).get(0)).toString(), (
/* 70 */         new BigDecimal("0.000189926147463")).setScale(scale, RoundingMode.HALF_UP).toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\titulo\TestCalculos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */