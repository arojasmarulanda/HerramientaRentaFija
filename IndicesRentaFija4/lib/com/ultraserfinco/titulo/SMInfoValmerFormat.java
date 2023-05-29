/*     */ package com.ultraserfinco.titulo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SMInfoValmerFormat
/*     */ {
/*  11 */   protected static final int[] INFOVALMERSM_FORMAT = new int[] { 1, 6, 7, 19, 31, 32, 40, 48, 56, 60, 63, 66, 69, 79, 91, 92, 
/*  12 */       100, 110, 120, 132, 135 };
/*     */   
/*  14 */   protected static final int[] CODIGO_DIAS_AL_VENCIMIENTO = new int[] { 0, 31, 62, 93, 182, 274, 366, 549, 731, 1096, 1461, 1826, 2191, 
/*  15 */       2556, 2921, 3286, 3651, 4016, 4381, 5111, 5841, 6571, 7301 };
/*  16 */   protected static final int[] CODIGOS_GRUPOS = new int[] { 15, 16, 17, 18, 19, 20, 21, 22, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 23, 24, 
/*  17 */       25, 26, 27 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getInfoFromLine(String line, SMIndexes info) {
/*  25 */     return line.substring(INFOVALMERSM_FORMAT[info.index] - 1, INFOVALMERSM_FORMAT[info.index + 1] - 1).trim();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int getCodigoDiasAlVencimiento(int numeroDiasAlVencimiento) throws IllegalArgumentException {
/*  35 */     int groupCounter = 0;
/*  36 */     if (numeroDiasAlVencimiento <= 0) return -9999; 
/*  37 */     if (numeroDiasAlVencimiento >= 7301) return 27; 
/*  38 */     while (numeroDiasAlVencimiento >= CODIGO_DIAS_AL_VENCIMIENTO[groupCounter]) {
/*  39 */       groupCounter++;
/*     */     }
/*  41 */     return CODIGOS_GRUPOS[groupCounter - 1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum SMIndexes
/*     */   {
/*  51 */     NEMO(2), MONEDA(9), TASA(10), CALIF(17), CLASE(19); public final int index;
/*     */     
/*     */     SMIndexes(int index) {
/*  54 */       this.index = index;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String grupoClaseToString(int grupoClase) {
/*  66 */     switch (grupoClase) {
/*     */       case -1:
/*  68 */         return "";
/*     */       case 5:
/*  70 */         return "DPNAL";
/*     */       case 10:
/*  72 */         return "DPNONAL";
/*     */       case 11:
/*  74 */         return "BANCOS";
/*     */       case 12:
/*  76 */         return "REAL";
/*     */       case 37:
/*  78 */         return "CORPFIN";
/*     */       case 38:
/*  80 */         return "HOLDING";
/*     */     } 
/*  82 */     return grupoClase;
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
/*     */   public static String grupoTasaToString(int grupoTasa) {
/*  94 */     switch (grupoTasa) {
/*     */       case -1:
/*  96 */         return "";
/*     */       case 1:
/*  98 */         return "DTF";
/*     */       case 2:
/* 100 */         return "FS";
/*     */       case 3:
/* 102 */         return "IPC";
/*     */       case 4:
/* 104 */         return "IPCBSOL";
/*     */       case 5:
/* 106 */         return "SUBTES";
/*     */       case 6:
/* 108 */         return "VARUVR";
/*     */       case 7:
/* 110 */         return "DEVAL";
/*     */       case 8:
/* 112 */         return "LIBOR";
/*     */       case 9:
/* 114 */         return "PRIME";
/*     */       case 10:
/* 116 */         return "EUDO";
/*     */       case 11:
/* 118 */         return "USURA";
/*     */       case 12:
/* 120 */         return "IBR";
/*     */       case 13:
/* 122 */         return "IB3";
/*     */     } 
/* 124 */     return grupoTasa;
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
/*     */   public static String grupoCalifToString(int grupoCalif) {
/* 137 */     switch (grupoCalif) {
/*     */       case -1:
/* 139 */         return "";
/*     */       case 100:
/* 141 */         return "MULTI";
/*     */       case 90:
/* 143 */         return "NACION";
/*     */       case 80:
/* 145 */         return "FOGAF";
/*     */       case 70:
/* 147 */         return "AAA";
/*     */       case 60:
/* 149 */         return "AA+";
/*     */       case 59:
/* 151 */         return "AA";
/*     */       case 58:
/* 153 */         return "AA-";
/*     */       case 50:
/* 155 */         return "A";
/*     */       case 40:
/* 157 */         return "BBB";
/*     */       case 30:
/* 159 */         return "BB";
/*     */       case 20:
/* 161 */         return "DD";
/*     */       case 10:
/* 163 */         return "EE";
/*     */     } 
/* 165 */     return grupoCalif;
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
/*     */   public static String grupoDiasAlVenToString(int grupoDiasAlVen) {
/* 177 */     if (grupoDiasAlVen == -1) return ""; 
/* 178 */     if (grupoDiasAlVen == 27) return String.valueOf(CODIGO_DIAS_AL_VENCIMIENTO[CODIGO_DIAS_AL_VENCIMIENTO.length - 1]) + "-";
/*     */     
/*     */     int indexOfGrupo;
/* 181 */     for (indexOfGrupo = 0; indexOfGrupo < CODIGOS_GRUPOS.length && 
/* 182 */       CODIGOS_GRUPOS[indexOfGrupo] != grupoDiasAlVen; indexOfGrupo++);
/*     */ 
/*     */     
/* 185 */     return String.valueOf(CODIGO_DIAS_AL_VENCIMIENTO[indexOfGrupo]) + "-" + (CODIGO_DIAS_AL_VENCIMIENTO[indexOfGrupo + 1] - 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\titulo\SMInfoValmerFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */