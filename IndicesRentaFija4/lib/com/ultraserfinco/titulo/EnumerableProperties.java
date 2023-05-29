/*    */ package com.ultraserfinco.titulo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class EnumerableProperties
/*    */ {
/*    */   public enum Estado
/*    */   {
/* 10 */     A, S;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public enum Periodicidad
/*    */   {
/* 17 */     D, Q, M, B, T, C, S, A, P, N;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public enum Modalidad
/*    */   {
/* 24 */     A, V, O;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public enum Moneda
/*    */   {
/* 31 */     COP, USD, UVR, TRM, NO_APLICA;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public enum TipoTasa
/*    */   {
/* 38 */     FIJA_SIMPLE, FIJA_COMP, VARIABLE;
/*    */     public static TipoTasa getTipoTasa(int numTipo) {
/* 40 */       switch (numTipo) {
/*    */         case 1:
/* 42 */           return FIJA_SIMPLE;
/*    */         case 2:
/* 44 */           return FIJA_COMP;
/*    */         case 3:
/* 46 */           return VARIABLE;
/*    */       } 
/* 48 */       throw new IllegalArgumentException("El tipo de tasa con este identificador no existe");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum TasaReferencia
/*    */   {
/* 57 */     FS, IPC, IB1, IB3, IP2, IP4, ICP, DTE, DTF, IPC3, IBR;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public enum TipoCalculo
/*    */   {
/* 64 */     PROMEDIO, ESTIMADO;
/*    */     public static TipoCalculo getTipoCalculo(int numTipo) {
/* 66 */       switch (numTipo) {
/*    */         case 1:
/* 68 */           return PROMEDIO;
/*    */         case 2:
/* 70 */           return ESTIMADO;
/*    */       } 
/* 72 */       throw new IllegalArgumentException("El tipo de calculo con este identificador no existe");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum BaseCalculo
/*    */   {
/* 81 */     NO_APLICA, CURVA_CERO, INDICE;
/*    */     public static BaseCalculo getBaseCalculo(int numTipo) {
/* 83 */       switch (numTipo) {
/*    */         case 0:
/* 85 */           return NO_APLICA;
/*    */         case 1:
/* 87 */           return CURVA_CERO;
/*    */         case 2:
/* 89 */           return INDICE;
/*    */       } 
/* 91 */       throw new IllegalArgumentException("La base de calculo con este identificador no existe");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\titulo\EnumerableProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */