/*    */ package com.ultraserfinco.indices;
/*    */ 
/*    */ import com.ultraserfinco.titulo.EnumerableProperties;
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
/*    */ public class TestIndice
/*    */ {
/*    */   @Test
/*    */   public void testNombreIndice() {
/* 21 */     Indice index1 = Indice.getIndiceFromInfo("NO_APLICA", 1, 60, 11, 19, EnumerableProperties.Moneda.COP);
/* 22 */     Indice index2 = Indice.getIndiceFromInfo("NO_APLICA", 3, 90, 38, 7, EnumerableProperties.Moneda.COP);
/* 23 */     Indice index3 = Indice.getIndiceFromInfo("NO_APLICA", 12, 10, 29, 27, EnumerableProperties.Moneda.COP);
/* 24 */     Indice index4 = Indice.getIndiceFromInfo("NO_APLICA", 6, 70, 12, 15, EnumerableProperties.Moneda.COP);
/* 25 */     Indice index5 = Indice.getIndiceFromInfo("NO_APLICA", -1, -1, 12, 15, EnumerableProperties.Moneda.COP);
/* 26 */     Indice index6 = Indice.getIndiceFromInfo("NO_APLICA", -1, 70, -1, -1, EnumerableProperties.Moneda.NO_APLICA);
/* 27 */     Indice index7 = Indice.getIndiceFromInfo("TFIT", -1, -1, -1, 21, EnumerableProperties.Moneda.COP);
/* 28 */     Assert.assertEquals(index1.getNombre(), "DTF_BANCOS_AA+_COP_182-273");
/* 29 */     Assert.assertEquals(index2.getNombre(), "IPC_HOLDING_NACION_COP_1826-2190");
/* 30 */     Assert.assertEquals(index3.getNombre(), "IBR_29_EE_COP_7301-");
/* 31 */     Assert.assertEquals(index4.getNombre(), "VARUVR_REAL_AAA_COP_0-30");
/* 32 */     Assert.assertEquals(index5.getNombre(), "REAL_COP_0-30");
/* 33 */     Assert.assertEquals(index6.getNombre(), "AAA");
/* 34 */     Assert.assertEquals(index7.getNombre(), "TFIT_COP_366-548");
/*    */   }
/*    */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\indices\TestIndice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */