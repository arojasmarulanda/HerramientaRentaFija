/*     */ package com.ultraserfinco.titulo;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.RoundingMode;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.time.LocalDate;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Deque;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
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
/*     */ public final class BaseDeDatosTitulos
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8724539679738090351L;
/*     */   private final Map<String, Titulo> mapIsinToTitulo;
/*     */   private final Map<String, Set<Titulo>> mapNemoToTitulos;
/*     */   private final Map<String, Integer> mapNemoToTasa;
/*     */   private final Map<String, Integer> mapNemoToCalif;
/*     */   private final Map<String, Integer> mapNemoToClase;
/*     */   private LocalDate fechaFinalBaseDatos;
/*     */   private int cacheSize;
/*     */   
/*     */   public BaseDeDatosTitulos(int cacheSize) {
/*  58 */     this.mapIsinToTitulo = new HashMap<>();
/*  59 */     this.mapNemoToTitulos = new HashMap<>();
/*  60 */     this.mapNemoToTasa = new HashMap<>();
/*  61 */     this.mapNemoToCalif = new HashMap<>();
/*  62 */     this.mapNemoToClase = new HashMap<>();
/*  63 */     this.cacheSize = cacheSize;
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
/*     */   public void actualizarDatos(String ubicacionArchivoSX, String ubicacionArchivoSM, LocalDate fecha) {
/*  76 */     this.fechaFinalBaseDatos = fecha;
/*  77 */     actualizarSX(ubicacionArchivoSX);
/*  78 */     actualizarSM(ubicacionArchivoSM);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void actualizarSM(String ubicacionArchivo) {
/*  88 */     Path ubicacion = Paths.get(ubicacionArchivo, new String[0]);
/*     */     try {
/*  90 */       Exception exception2, exception1 = null;
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
/*     */     }
/* 112 */     catch (IOException ioex) {
/* 113 */       System.err.format("IOException: %s%n", new Object[] { ioex });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void actualizarSX(String ubicacionArchivo) {
/* 124 */     Path ubicacion = Paths.get(ubicacionArchivo, new String[0]);
/*     */     try {
/* 126 */       Exception exception2, exception1 = null;
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
/*     */     }
/* 151 */     catch (IOException ioex) {
/* 152 */       System.err.format("IOException: %s%n", new Object[] { ioex });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void eliminarTituloVencido(Titulo titulo) {
/* 161 */     if (titulo.getDiasAlVencimiento(this.fechaFinalBaseDatos) <= 1) {
/* 162 */       eliminarTitulo(titulo);
/*     */     }
/*     */   }
/*     */   
/*     */   private void eliminarTitulo(Titulo titulo) {
/* 167 */     this.mapIsinToTitulo.remove(titulo.getIsin());
/*     */     
/* 169 */     if (((Set)this.mapNemoToTitulos.get(titulo.getNemo())).size() <= 1) {
/* 170 */       this.mapNemoToTitulos.remove(titulo.getNemo());
/* 171 */       this.mapNemoToTasa.remove(titulo.getNemo());
/* 172 */       this.mapNemoToCalif.remove(titulo.getNemo());
/* 173 */       this.mapNemoToClase.remove(titulo.getNemo());
/*     */       
/*     */       return;
/*     */     } 
/* 177 */     ((Set)this.mapNemoToTitulos.get(titulo.getNemo())).remove(titulo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Titulo getTituloFromISIN(String ISIN) throws ArgumentNotFoundException {
/* 188 */     if (!this.mapIsinToTitulo.containsKey(ISIN)) throw new ArgumentNotFoundException(ISIN); 
/* 189 */     return this.mapIsinToTitulo.get(ISIN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Titulo> getTitulosFromNemo(String nemo) throws ArgumentNotFoundException {
/* 200 */     if (!this.mapNemoToTitulos.containsKey(nemo)) throw new ArgumentNotFoundException(nemo); 
/* 201 */     return this.mapNemoToTitulos.get(nemo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Titulo> getTitulosFromTasa(int tasa) {
/* 211 */     Set<Titulo> titulos = new HashSet<>();
/* 212 */     for (String nemo : this.mapNemoToTasa.keySet()) {
/* 213 */       if (((Integer)this.mapNemoToTasa.get(nemo)).intValue() == tasa) {
/* 214 */         titulos.addAll(this.mapNemoToTitulos.get(nemo));
/*     */       }
/*     */     } 
/*     */     
/* 218 */     return titulos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Titulo> getTitulosFromGrupoCalif(int grupoCalif) {
/* 228 */     Set<Titulo> titulos = new HashSet<>();
/* 229 */     for (String nemo : this.mapNemoToCalif.keySet()) {
/* 230 */       if (((Integer)this.mapNemoToCalif.get(nemo)).intValue() == grupoCalif) {
/* 231 */         titulos.addAll(this.mapNemoToTitulos.get(nemo));
/*     */       }
/*     */     } 
/*     */     
/* 235 */     return titulos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Titulo> getTitulosFromClase(int clase) {
/* 245 */     Set<Titulo> titulos = new HashSet<>();
/* 246 */     for (String nemo : this.mapNemoToClase.keySet()) {
/* 247 */       if (((Integer)this.mapNemoToClase.get(nemo)).intValue() == clase) {
/* 248 */         titulos.addAll(this.mapNemoToTitulos.get(nemo));
/*     */       }
/*     */     } 
/*     */     
/* 252 */     return titulos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<Titulo> getTitulosFromPartialNemo(String partialNemo) {
/* 261 */     Set<Titulo> titulos = new HashSet<>();
/* 262 */     for (String nemo : this.mapNemoToTitulos.keySet()) {
/* 263 */       if (nemo.startsWith(partialNemo)) {
/* 264 */         titulos.addAll(this.mapNemoToTitulos.get(nemo));
/*     */       }
/*     */     } 
/*     */     
/* 268 */     return titulos;
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
/*     */   public Set<Titulo> getTitulosFromInfo(String nemo, int tasa, int grupoCalif, int clase, int grupoDiasAlV, EnumerableProperties.Moneda moneda) {
/* 280 */     Deque<Set<Titulo>> titulosFromParam = new LinkedList<>();
/* 281 */     if (!nemo.startsWith("NO_")) titulosFromParam.addLast(getTitulosFromPartialNemo(nemo)); 
/* 282 */     if (tasa != -1) titulosFromParam.addLast(getTitulosFromTasa(tasa)); 
/* 283 */     if (grupoCalif != -1) titulosFromParam.addLast(getTitulosFromGrupoCalif(grupoCalif)); 
/* 284 */     if (clase != -1) titulosFromParam.addLast(getTitulosFromClase(clase)); 
/* 285 */     Set<Titulo> lowerSet = titulosFromParam.removeLast();
/* 286 */     while (!titulosFromParam.isEmpty()) {
/* 287 */       lowerSet.retainAll(titulosFromParam.removeLast());
/*     */     }
/* 289 */     Iterator<Titulo> it = lowerSet.iterator();
/*     */     
/* 291 */     while (it.hasNext()) {
/* 292 */       Titulo titulo = it.next();
/* 293 */       if (grupoDiasAlV != -1 && SMInfoValmerFormat.getCodigoDiasAlVencimiento(titulo.getDiasAlVencimiento(this.fechaFinalBaseDatos)) != grupoDiasAlV) {
/* 294 */         it.remove();
/*     */         continue;
/*     */       } 
/* 297 */       if (!moneda.equals(EnumerableProperties.Moneda.NO_APLICA) && !moneda.equals(titulo.getMoneda())) {
/* 298 */         it.remove();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 303 */     return lowerSet;
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
/*     */ 
/*     */   
/*     */   public List<BigDecimal> getValoresPromedioDeGrupo(Set<Titulo> titulos, int scale, Titulo.DatosTitulo... datos) {
/* 320 */     double[] results = new double[datos.length];
/*     */     
/* 322 */     for (int i = 0; i < datos.length; i++) {
/* 323 */       results[i] = 0.0D;
/*     */     }
/* 325 */     int numeroTitulos = titulos.size();
/*     */ 
/*     */     
/* 328 */     for (Titulo titulo : titulos) {
/* 329 */       if (titulo.isNew()) {
/* 330 */         numeroTitulos--;
/*     */         continue;
/*     */       } 
/* 333 */       if (!titulo.getUltimaActualizacion().equals(this.fechaFinalBaseDatos)) {
/* 334 */         eliminarTitulo(titulo);
/* 335 */         numeroTitulos--;
/* 336 */         System.out.println("ISIN: " + titulo.getIsin() + " NEMO: " + titulo.getNemo() + " ULT_ACT: " + titulo.getUltimaActualizacion() + 
/* 337 */             " AT: " + this.fechaFinalBaseDatos + " DIAS_AL_V: " + titulo.getDiasAlVencimiento(this.fechaFinalBaseDatos));
/*     */         continue;
/*     */       } 
/* 340 */       for (int m = 0; m < datos.length; m++) {
/* 341 */         results[m] = results[m] + titulo.getUltimoDatoTitulo(datos[m]);
/*     */       }
/*     */     } 
/*     */     
/* 345 */     if (numeroTitulos <= 0) numeroTitulos = 1;
/*     */     
/* 347 */     for (int j = 0; j < datos.length; j++) {
/* 348 */       results[j] = results[j] / numeroTitulos;
/*     */     }
/*     */ 
/*     */     
/* 352 */     List<BigDecimal> bigDecList = new ArrayList<>();
/* 353 */     for (int k = 0; k < datos.length; k++) {
/* 354 */       bigDecList.add((new BigDecimal(results[k])).setScale(scale, RoundingMode.HALF_UP));
/*     */     }
/* 356 */     return bigDecList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> getNemoList() {
/* 366 */     Set<String> nemos = new TreeSet<>(this.mapNemoToTitulos.keySet());
/* 367 */     return nemos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfTitles() {
/* 375 */     return this.mapIsinToTitulo.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalDate getFechaFinalBaseDatos() {
/* 382 */     return LocalDate.parse(this.fechaFinalBaseDatos.toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\titulo\BaseDeDatosTitulos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */