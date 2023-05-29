/*    */ package com.ultraserfinco.titulo;
/*    */ 
/*    */ import java.time.LocalDate;
/*    */ import java.time.format.DateTimeFormatter;
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
/*    */ public final class SXInfoValmerFormat
/*    */ {
/* 18 */   protected static final int[] INFOVALMERSX_FORMAT = new int[] { 1, 8, 9, 21, 33, 51, 52, 60, 68, 76, 77, 78, 82, 86, 87, 91, 106, 
/* 19 */       110, 111, 130, 138, 157, 169, 188, 200, 211, 221, 231, 241, 251, 261, 270 };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Titulo getTituloFromLine(String line, int cacheSize) {
/* 30 */     String nemo = getInfoFromLine(line, SXIndexes.NEMO);
/* 31 */     String isin = getInfoFromLine(line, SXIndexes.ISIN);
/* 32 */     EnumerableProperties.Estado estado = EnumerableProperties.Estado.valueOf(getInfoFromLine(line, SXIndexes.ESTADO));
/*    */     
/* 34 */     LocalDate ultimaActualizacion = LocalDate.parse(getInfoFromLine(line, SXIndexes.ULT_ACTU), DateTimeFormatter.BASIC_ISO_DATE);
/* 35 */     LocalDate fechaEmision = LocalDate.parse(getInfoFromLine(line, SXIndexes.EMISION), DateTimeFormatter.BASIC_ISO_DATE);
/* 36 */     LocalDate fechaVencimiento = LocalDate.parse(getInfoFromLine(line, SXIndexes.VENCIM), DateTimeFormatter.BASIC_ISO_DATE);
/*    */     
/* 38 */     EnumerableProperties.Periodicidad periodicidad = EnumerableProperties.Periodicidad.valueOf(getInfoFromLine(line, SXIndexes.PERIOD));
/* 39 */     EnumerableProperties.Modalidad modalidad = EnumerableProperties.Modalidad.valueOf(getInfoFromLine(line, SXIndexes.MODAL));
/* 40 */     EnumerableProperties.Moneda moneda = EnumerableProperties.Moneda.valueOf(getInfoFromLine(line, SXIndexes.MONEDA).trim());
/* 41 */     EnumerableProperties.TipoTasa tipoTasa = EnumerableProperties.TipoTasa.getTipoTasa(Integer.parseInt(getInfoFromLine(line, SXIndexes.T_TASA)));
/* 42 */     EnumerableProperties.TasaReferencia tasaReferencia = EnumerableProperties.TasaReferencia.valueOf(getInfoFromLine(line, SXIndexes.TASA_REF).trim());
/* 43 */     String spread = getInfoFromLine(line, SXIndexes.SPREAD);
/* 44 */     EnumerableProperties.TipoCalculo tipoCalculo = EnumerableProperties.TipoCalculo.getTipoCalculo(Integer.parseInt(getInfoFromLine(line, SXIndexes.T_CALC)));
/* 45 */     EnumerableProperties.BaseCalculo baseCalculo = EnumerableProperties.BaseCalculo.getBaseCalculo(Integer.parseInt(getInfoFromLine(line, SXIndexes.B_CALC)));
/* 46 */     String precioSucio = getInfoFromLine(line, SXIndexes.P_SUCIO);
/* 47 */     String precioLimpio = getInfoFromLine(line, SXIndexes.P_LIMPIO);
/* 48 */     String tir = getInfoFromLine(line, SXIndexes.TIR);
/* 49 */     String duracion = getInfoFromLine(line, SXIndexes.DURACION);
/* 50 */     String duracionMod = getInfoFromLine(line, SXIndexes.DURACION_MOD);
/* 51 */     String convexidad = getInfoFromLine(line, SXIndexes.CONVEX);
/* 52 */     String cuponCorrido = getInfoFromLine(line, SXIndexes.CUPON);
/* 53 */     String margen = getInfoFromLine(line, SXIndexes.MARGEN);
/* 54 */     String calificacion = getInfoFromLine(line, SXIndexes.CALIF).trim();
/* 55 */     return new Titulo(nemo, isin, estado, fechaEmision, fechaVencimiento, periodicidad, modalidad, moneda, tipoTasa, 
/* 56 */         tasaReferencia, spread, tipoCalculo, baseCalculo, precioSucio, precioLimpio, ultimaActualizacion, tir, 
/* 57 */         duracion, duracionMod, convexidad, cuponCorrido, margen, calificacion, cacheSize);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected static String getInfoFromLine(String line, SXIndexes info) {
/* 66 */     return line.substring(INFOVALMERSX_FORMAT[info.index] - 1, INFOVALMERSX_FORMAT[info.index + 1] - 1).trim();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum SXIndexes
/*    */   {
/* 75 */     CALIF(
/*    */ 
/*    */       
/* 78 */       30), MARGEN(29), CUPON(28), CONVEX(27), DURACION_MOD(26), DURACION(25), TIR(24), P_LIMPIO(22),
/* 79 */     P_SUCIO(18), B_CALC(17), T_CALC(16), SPREAD(15), TASA_REF(14), T_TASA(13), MONEDA(12), MODAL(10), PERIOD(9),
/* 80 */     VENCIM(8), EMISION(7), ULT_ACTU(6), ESTADO(5), ISIN(3), NEMO(2);
/*    */     public final int index;
/*    */     
/*    */     SXIndexes(int index) {
/* 84 */       this.index = index;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\titulo\SXInfoValmerFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */