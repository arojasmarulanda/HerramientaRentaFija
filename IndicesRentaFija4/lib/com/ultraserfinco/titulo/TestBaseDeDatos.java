/*     */ package com.ultraserfinco.titulo;
/*     */ 
/*     */ import java.time.LocalDate;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.junit.Assert;
/*     */ import org.junit.Before;
/*     */ import org.junit.Test;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestBaseDeDatos
/*     */ {
/*     */   @Before
/*     */   public void setUp() throws Exception {}
/*     */   
/*     */   public BaseDeDatosTitulos startBaseDeDatos() {
/*  40 */     BaseDeDatosTitulos baseDatos = new BaseDeDatosTitulos(3);
/*  41 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SP082217.002", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082217.002", LocalDate.of(2017, 8, 22));
/*  42 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082317.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082317.002", LocalDate.of(2017, 8, 23));
/*  43 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082417.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082417.002", LocalDate.of(2017, 8, 24));
/*  44 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082517.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082517.002", LocalDate.of(2017, 8, 25));
/*  45 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082617.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082617.003", LocalDate.of(2017, 8, 26));
/*  46 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082717.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082717.004", LocalDate.of(2017, 8, 27));
/*  47 */     return baseDatos;
/*     */   }
/*     */   
/*     */   @Test
/*     */   public void testActualizarDatosArchivoUnico() throws ArgumentNotFoundException {
/*  52 */     BaseDeDatosTitulos baseDatos = new BaseDeDatosTitulos(3);
/*  53 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SP082217.002", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082217.002", LocalDate.of(2017, 8, 22));
/*  54 */     Assert.assertEquals("CDTBGA95", baseDatos.getTituloFromISIN("CDTBGA950006").getNemo());
/*  55 */     Assert.assertEquals("BPEMINHTA4", baseDatos.getTituloFromISIN("COL17CB02HX8").getNemo());
/*  56 */     Assert.assertEquals("CDTBCBS0V", baseDatos.getTituloFromISIN("COB07CD06J93").getNemo());
/*  57 */     Assert.assertEquals(105.871D, baseDatos.getTituloFromISIN("CDTBGA950006").getUltimoPrecioCalculado(), 1.0E-5D);
/*  58 */     Assert.assertEquals(327.156D, baseDatos.getTituloFromISIN("COL17CB02HX8").getUltimoPrecioCalculado(), 1.0E-5D);
/*  59 */     Assert.assertEquals(100.628D, baseDatos.getTituloFromISIN("COB07CD06J93").getUltimoPrecioCalculado(), 1.0E-5D);
/*  60 */     Assert.assertEquals("2024-04-19", baseDatos.getTituloFromISIN("CDTBGA950006").getFechaVencimiento().toString());
/*  61 */     Assert.assertEquals("2028-08-09", baseDatos.getTituloFromISIN("COL17CB02HX8").getFechaVencimiento().toString());
/*  62 */     Assert.assertEquals("2017-09-24", baseDatos.getTituloFromISIN("COB07CD06J93").getFechaVencimiento().toString());
/*     */   }
/*     */   
/*     */   @Test
/*     */   public void testActualizarDatosMultiplesArchivos() throws ArgumentNotFoundException {
/*  67 */     BaseDeDatosTitulos baseDatos = new BaseDeDatosTitulos(3);
/*  68 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SP082217.002", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082217.002", LocalDate.of(2017, 8, 22));
/*  69 */     Assert.assertEquals(100.119D, baseDatos.getTituloFromISIN("COJ05CD01IQ7").getUltimoPrecioCalculado(), 1.0E-5D);
/*  70 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082317.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082317.002", LocalDate.of(2017, 8, 23));
/*  71 */     Assert.assertEquals(100.137D, baseDatos.getTituloFromISIN("COJ05CD01IQ7").getUltimoPrecioCalculado(), 1.0E-5D);
/*  72 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082417.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082417.002", LocalDate.of(2017, 8, 24));
/*  73 */     Assert.assertEquals(100.154D, baseDatos.getTituloFromISIN("COJ05CD01IQ7").getUltimoPrecioCalculado(), 1.0E-5D);
/*  74 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082517.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082517.002", LocalDate.of(2017, 8, 25));
/*  75 */     Assert.assertEquals(100.17D, baseDatos.getTituloFromISIN("COJ05CD01IQ7").getUltimoPrecioCalculado(), 1.0E-5D);
/*  76 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082617.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082617.003", LocalDate.of(2017, 8, 26));
/*  77 */     Assert.assertEquals(100.186D, baseDatos.getTituloFromISIN("COJ05CD01IQ7").getUltimoPrecioCalculado(), 1.0E-5D);
/*  78 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082717.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082717.004", LocalDate.of(2017, 8, 27));
/*  79 */     Assert.assertEquals(100.201D, baseDatos.getTituloFromISIN("COJ05CD01IQ7").getUltimoPrecioCalculado(), 1.0E-5D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Test
/*     */   public void testGetTitulosFromNemo() throws ArgumentNotFoundException {
/*  95 */     BaseDeDatosTitulos baseDatos = new BaseDeDatosTitulos(3);
/*  96 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SP082217.002", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082217.002", LocalDate.of(2017, 8, 22));
/*  97 */     int tituloCounter = 0;
/*  98 */     for (String nemo : baseDatos.getNemoList()) {
/*  99 */       Set<Titulo> titulos = baseDatos.getTitulosFromNemo(nemo);
/* 100 */       for (Titulo titulo : titulos) {
/* 101 */         Assert.assertEquals("El nemo de cada titulo deberia corresponder al nemo de su conjunto", titulo.getNemo(), nemo);
/* 102 */         tituloCounter++;
/*     */       } 
/*     */     } 
/* 105 */     Assert.assertEquals("El numero de titulos contado desde los conjuntos de nemosdeberia igualar al numero de titulos contados en la base de datos", 
/*     */         
/* 107 */         tituloCounter, baseDatos.getNumberOfTitles());
/* 108 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082317.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082317.002", LocalDate.of(2017, 8, 23));
/* 109 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082417.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082417.002", LocalDate.of(2017, 8, 24));
/* 110 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082517.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082517.002", LocalDate.of(2017, 8, 25));
/* 111 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082617.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082617.003", LocalDate.of(2017, 8, 26));
/* 112 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082717.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082717.004", LocalDate.of(2017, 8, 27));
/* 113 */     tituloCounter = 0;
/* 114 */     for (String nemo : baseDatos.getNemoList()) {
/* 115 */       Set<Titulo> titulos = baseDatos.getTitulosFromNemo(nemo);
/* 116 */       for (Titulo titulo : titulos) {
/* 117 */         Assert.assertEquals(titulo.getNemo(), nemo);
/* 118 */         tituloCounter++;
/*     */       } 
/*     */     } 
/* 121 */     Assert.assertEquals(tituloCounter, baseDatos.getNumberOfTitles());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Test
/*     */   public void testActualizarDatosFromSM() throws ArgumentNotFoundException {
/* 128 */     BaseDeDatosTitulos baseDatos = startBaseDeDatos();
/* 129 */     Assert.assertTrue(baseDatos.getTitulosFromTasa(13).containsAll(baseDatos.getTitulosFromNemo("CDTDVI80T")));
/* 130 */     Assert.assertTrue(baseDatos.getTitulosFromTasa(12).containsAll(baseDatos.getTitulosFromNemo("CDTWWB80")));
/* 131 */     Assert.assertTrue(baseDatos.getTitulosFromTasa(3).containsAll(baseDatos.getTitulosFromNemo("CDTWWB90")));
/* 132 */     for (Titulo titulo : baseDatos.getTitulosFromTasa(12)) {
/* 133 */       Assert.assertTrue(titulo.getTasaReferencia().toString().startsWith("IB"));
/*     */     }
/*     */     
/* 136 */     Set<Titulo> test = baseDatos.getTitulosFromGrupoCalif(10);
/* 137 */     test.retainAll(baseDatos.getTitulosFromNemo("CDTBMMS0V"));
/* 138 */     Assert.assertTrue(baseDatos.getTitulosFromGrupoCalif(70).containsAll(baseDatos.getTitulosFromNemo("CDTCLP90P")));
/* 139 */     Assert.assertTrue(baseDatos.getTitulosFromGrupoCalif(70).containsAll(baseDatos.getTitulosFromNemo("CDTBCX80")));
/*     */     
/* 141 */     Assert.assertTrue(baseDatos.getTitulosFromClase(2).containsAll(baseDatos.getTitulosFromNemo("TUVT17230223")));
/* 142 */     Assert.assertTrue(baseDatos.getTitulosFromClase(11).containsAll(baseDatos.getTitulosFromNemo("CDTMIAS0VD")));
/* 143 */     Assert.assertTrue(baseDatos.getTitulosFromClase(11).containsAll(baseDatos.getTitulosFromNemo("CDTFAN10")));
/* 144 */     Assert.assertTrue(baseDatos.getTitulosFromClase(15).containsAll(baseDatos.getTitulosFromNemo("BPENSION2R")));
/* 145 */     Assert.assertTrue(baseDatos.getTitulosFromClase(37).containsAll(baseDatos.getTitulosFromNemo("CDTCFC90")));
/*     */   }
/*     */   
/*     */   @Test
/*     */   public void testRemovalOfTitle() throws ArgumentNotFoundException {
/* 150 */     BaseDeDatosTitulos baseDatos = new BaseDeDatosTitulos(3);
/* 151 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SP082217.002", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082217.002", LocalDate.of(2017, 8, 22));
/* 152 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082317.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082317.002", LocalDate.of(2017, 8, 23));
/* 153 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082417.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082417.002", LocalDate.of(2017, 8, 24));
/* 154 */     baseDatos.actualizarDatos(String.valueOf(System.getProperty("user.dir")) + "/testfiles/SX082517.001", String.valueOf(System.getProperty("user.dir")) + "/testfiles/SM082517.002", LocalDate.of(2017, 8, 25));
/*     */     try {
/* 156 */       baseDatos.getTituloFromISIN("COB07CD02GL3");
/* 157 */       Assert.fail("La excepcion ArgumentNotFoundException deberia ser lanzada porque el titulo se encuentra vencido");
/* 158 */     } catch (ArgumentNotFoundException anfEx) {
/* 159 */       Assert.assertEquals(anfEx.getMessage(), (new ArgumentNotFoundException("COB07CD02GL3")).getMessage());
/*     */     } 
/* 161 */     Set<String> isinList = new HashSet<>();
/* 162 */     for (Titulo titulo : baseDatos.getTitulosFromNemo("CDTBCB80P")) {
/* 163 */       isinList.add(titulo.getIsin());
/*     */     }
/* 165 */     Assert.assertFalse(isinList.contains("COB07CD02GL3"));
/*     */   }
/*     */ 
/*     */   
/*     */   @Test
/*     */   public void testGetTitulosFromInfo() throws ArgumentNotFoundException {
/* 171 */     BaseDeDatosTitulos baseDatos = startBaseDeDatos();
/* 172 */     Set<Titulo> titulos = baseDatos.getTitulosFromInfo("NO_APLICA", 3, 70, 11, 19, EnumerableProperties.Moneda.valueOf("COP"));
/* 173 */     for (Titulo titulo : titulos) {
/* 174 */       Assert.assertTrue("Titulo con ISIN: " + titulo.getIsin(), titulo.getTasaReferencia().equals(EnumerableProperties.TasaReferencia.IPC));
/* 175 */       Assert.assertTrue("Titulo con ISIN: " + titulo.getIsin(), (titulo.getDiasAlVencimiento(LocalDate.of(2017, 8, 27)) < 274));
/* 176 */       Assert.assertTrue("Titulo con ISIN: " + titulo.getIsin(), (titulo.getDiasAlVencimiento(LocalDate.of(2017, 8, 27)) > 181));
/* 177 */       Assert.assertTrue("Titulo con ISIN: " + titulo.getIsin(), titulo.getMoneda().equals(EnumerableProperties.Moneda.COP));
/*     */     } 
/* 179 */     Assert.assertTrue(baseDatos.getTitulosFromTasa(3).containsAll(titulos));
/* 180 */     Assert.assertTrue(baseDatos.getTitulosFromGrupoCalif(70).containsAll(titulos));
/* 181 */     Assert.assertTrue(baseDatos.getTitulosFromClase(11).containsAll(titulos));
/* 182 */     Set<Titulo> titulos2 = baseDatos.getTitulosFromInfo("NO_APLICA", 13, -1, -1, -1, EnumerableProperties.Moneda.NO_APLICA);
/* 183 */     Assert.assertFalse("El conjunto de titulos no puede ser vacio", titulos2.isEmpty());
/* 184 */     for (Titulo titulo : titulos2) {
/* 185 */       Assert.assertTrue("Titulo con ISIN: " + titulo.getIsin(), titulo.getTasaReferencia().equals(EnumerableProperties.TasaReferencia.IB3));
/*     */     }
/* 187 */     baseDatos.getTitulosFromTasa(13).containsAll(titulos2);
/* 188 */     Set<Titulo> titulos3 = baseDatos.getTitulosFromInfo("NO_APLICA", 12, 70, -1, -1, EnumerableProperties.Moneda.NO_APLICA);
/* 189 */     Assert.assertFalse("El conjunto de titulos no puede ser vacio", titulos3.isEmpty());
/* 190 */     for (Titulo titulo : titulos3) {
/* 191 */       Assert.assertTrue("Titulo con ISIN: " + titulo.getIsin(), titulo.getTasaReferencia().equals(EnumerableProperties.TasaReferencia.IB1));
/*     */     }
/* 193 */     Assert.assertTrue(baseDatos.getTitulosFromTasa(12).containsAll(titulos3));
/* 194 */     Assert.assertTrue(baseDatos.getTitulosFromGrupoCalif(70).containsAll(titulos3));
/*     */     
/* 196 */     Set<Titulo> titulos4 = baseDatos.getTitulosFromPartialNemo("TFIT");
/* 197 */     Assert.assertFalse("El conjunto de titulos no puede ser vacio", titulos4.isEmpty());
/* 198 */     for (Titulo titulo : titulos4) {
/* 199 */       Assert.assertTrue("Titulo con ISIN: " + titulo.getIsin(), titulo.getNemo().startsWith("TFIT"));
/*     */     }
/*     */ 
/*     */     
/* 203 */     Set<Titulo> titulos5 = baseDatos.getTitulosFromInfo("TFIT", -1, -1, -1, 21, EnumerableProperties.Moneda.valueOf("COP"));
/* 204 */     for (Titulo titulo : titulos5)
/* 205 */       Assert.assertTrue("Titulo con ISIN: " + titulo.getIsin(), titulo.getNemo().startsWith("TFIT")); 
/*     */   }
/*     */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\titulo\TestBaseDeDatos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */