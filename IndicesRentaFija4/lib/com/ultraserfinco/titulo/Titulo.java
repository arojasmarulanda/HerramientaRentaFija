/*     */ package com.ultraserfinco.titulo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.time.LocalDate;
/*     */ import java.time.format.DateTimeFormatter;
/*     */ import java.time.temporal.ChronoUnit;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Deque;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.math3.analysis.function.Log;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Titulo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4421286121143107533L;
/*     */   private final String nemo;
/*     */   private final String isin;
/*     */   private EnumerableProperties.Estado estado;
/*     */   private final LocalDate fechaEmision;
/*     */   private LocalDate fechaVencimiento;
/*     */   private LocalDate ultimaActualizacion;
/*     */   private final EnumerableProperties.Periodicidad periodicidad;
/*     */   private final EnumerableProperties.Modalidad modalidad;
/*     */   private final EnumerableProperties.Moneda moneda;
/*     */   private final EnumerableProperties.TipoTasa tipoTasa;
/*     */   private final EnumerableProperties.TasaReferencia tasaReferencia;
/*     */   private final double spread;
/*     */   private final EnumerableProperties.TipoCalculo tipoCalculo;
/*     */   private final EnumerableProperties.BaseCalculo baseCalculo;
/*     */   private final Deque<Double> precioSucio;
/*     */   private final Deque<Double> precioLimpio;
/*     */   private final Deque<Double> tir;
/*     */   private final Deque<Double> duracion;
/*     */   private final Deque<Double> duracionMod;
/*     */   private final Deque<Double> convexidad;
/*     */   private final Deque<Double> cuponCorrido;
/*     */   private final Deque<Double> margen;
/*     */   private final Deque<String> calificacion;
/*     */   private final int cacheSize;
/*     */   
/*     */   public Titulo(String nemo, String isin, EnumerableProperties.Estado estado, LocalDate fechaEmision, LocalDate fechaVencimiento, EnumerableProperties.Periodicidad periodicidad, EnumerableProperties.Modalidad modalidad, EnumerableProperties.Moneda moneda, EnumerableProperties.TipoTasa tipoTasa, EnumerableProperties.TasaReferencia tasaReferencia, String spread, EnumerableProperties.TipoCalculo tipoCalculo, EnumerableProperties.BaseCalculo baseCalculo, String precioSucio, String precioLimpio, LocalDate ultimaActualizacion, String tir, String duracion, String duracionMod, String convexidad, String cuponCorrido, String margen, String calificacion, int cacheSize) {
/*  73 */     this.nemo = nemo;
/*  74 */     this.isin = isin;
/*  75 */     this.estado = estado;
/*  76 */     this.fechaEmision = fechaEmision;
/*  77 */     this.fechaVencimiento = fechaVencimiento;
/*  78 */     this.periodicidad = periodicidad;
/*  79 */     this.modalidad = modalidad;
/*  80 */     this.moneda = moneda;
/*  81 */     this.tipoTasa = tipoTasa;
/*  82 */     this.tasaReferencia = tasaReferencia;
/*  83 */     this.spread = Double.parseDouble(spread);
/*  84 */     this.tipoCalculo = tipoCalculo;
/*  85 */     this.baseCalculo = baseCalculo;
/*     */     
/*  87 */     this.precioSucio = new ArrayDeque<>(cacheSize);
/*  88 */     this.precioSucio.addLast(Double.valueOf(Double.parseDouble(precioSucio)));
/*  89 */     this.precioLimpio = new ArrayDeque<>(cacheSize);
/*  90 */     this.precioLimpio.addLast(Double.valueOf(Double.parseDouble(precioLimpio)));
/*     */     
/*  92 */     this.ultimaActualizacion = ultimaActualizacion;
/*     */     
/*  94 */     this.tir = new ArrayDeque<>(cacheSize);
/*  95 */     this.tir.addLast(Double.valueOf(Double.parseDouble(tir)));
/*     */     
/*  97 */     this.duracion = new ArrayDeque<>(cacheSize);
/*  98 */     this.duracion.addLast(Double.valueOf(Double.parseDouble(duracion)));
/*  99 */     this.duracionMod = new ArrayDeque<>(cacheSize);
/* 100 */     this.duracionMod.addLast(Double.valueOf(Double.parseDouble(duracionMod)));
/*     */     
/* 102 */     this.convexidad = new ArrayDeque<>(cacheSize);
/* 103 */     this.convexidad.addLast(Double.valueOf(Double.parseDouble(convexidad)));
/*     */     
/* 105 */     this.cuponCorrido = new ArrayDeque<>(cacheSize);
/* 106 */     this.cuponCorrido.addLast(Double.valueOf(Double.parseDouble(cuponCorrido)));
/*     */     
/* 108 */     this.margen = new ArrayDeque<>(cacheSize);
/* 109 */     this.margen.addLast(Double.valueOf(Double.parseDouble(margen)));
/*     */     
/* 111 */     this.calificacion = new ArrayDeque<>(cacheSize);
/* 112 */     this.calificacion.addLast(calificacion);
/*     */     
/* 114 */     this.cacheSize = cacheSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actualizarTitulo(String line) {
/* 123 */     this.estado = EnumerableProperties.Estado.valueOf(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.ESTADO));
/* 124 */     this.ultimaActualizacion = LocalDate.parse(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.ULT_ACTU), DateTimeFormatter.BASIC_ISO_DATE);
/*     */     
/* 126 */     if (this.precioSucio.size() == this.cacheSize) {
/* 127 */       this.precioSucio.removeFirst();
/* 128 */       this.precioLimpio.removeFirst();
/* 129 */       this.tir.removeFirst();
/* 130 */       this.duracion.removeFirst();
/* 131 */       this.duracionMod.removeFirst();
/* 132 */       this.convexidad.removeFirst();
/* 133 */       this.cuponCorrido.removeFirst();
/* 134 */       this.margen.removeFirst();
/* 135 */       this.calificacion.removeFirst();
/*     */     } 
/* 137 */     this.precioSucio.addLast(Double.valueOf(Double.parseDouble(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.P_SUCIO))));
/* 138 */     this.precioLimpio.addLast(Double.valueOf(Double.parseDouble(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.P_LIMPIO))));
/* 139 */     this.tir.addLast(Double.valueOf(Double.parseDouble(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.TIR))));
/* 140 */     this.duracion.addLast(Double.valueOf(Double.parseDouble(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.DURACION))));
/* 141 */     this.duracionMod.addLast(Double.valueOf(Double.parseDouble(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.DURACION_MOD))));
/* 142 */     this.convexidad.addLast(Double.valueOf(Double.parseDouble(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.CONVEX))));
/* 143 */     this.cuponCorrido.addLast(Double.valueOf(Double.parseDouble(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.CUPON))));
/* 144 */     this.margen.addLast(Double.valueOf(Double.parseDouble(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.MARGEN))));
/* 145 */     this.calificacion.addLast(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.CALIF));
/* 146 */     this.fechaVencimiento = LocalDate.parse(SXInfoValmerFormat.getInfoFromLine(line, SXInfoValmerFormat.SXIndexes.VENCIM), DateTimeFormatter.BASIC_ISO_DATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDiasAlVencimiento(LocalDate lastUpdate) {
/* 155 */     return (int)ChronoUnit.DAYS.between(lastUpdate, this.fechaVencimiento);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getUltimoPrecioCalculado() {
/* 163 */     return ((Double)this.precioSucio.getLast()).doubleValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNemo() {
/* 170 */     return this.nemo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIsin() {
/* 177 */     return this.isin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumerableProperties.Estado getEstado() {
/* 184 */     return this.estado;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalDate getFechaEmision() {
/* 191 */     return this.fechaEmision;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalDate getFechaVencimiento() {
/* 198 */     return this.fechaVencimiento;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumerableProperties.TasaReferencia getTasaReferencia() {
/* 205 */     return this.tasaReferencia;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalDate getUltimaActualizacion() {
/* 212 */     return this.ultimaActualizacion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUltimaCalificacion() {
/* 219 */     return this.calificacion.getLast();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumerableProperties.Moneda getMoneda() {
/* 226 */     return this.moneda;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double getUltimoCambioPrecio() {
/*     */     Iterator<Double> it;
/* 234 */     if (Math.abs(((Double)this.precioSucio.getLast()).doubleValue() - ((Double)this.precioLimpio.getLast()).doubleValue()) < 1.0E-4D) {
/* 235 */       it = this.precioLimpio.descendingIterator();
/*     */     } else {
/* 237 */       it = this.precioSucio.descendingIterator();
/*     */     } 
/* 239 */     double ultPrecio = ((Double)it.next()).doubleValue();
/* 240 */     double penUltPrecio = ((Double)it.next()).doubleValue();
/*     */     
/* 242 */     return (new Log()).value(ultPrecio / penUltPrecio);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getUltimoDatoTitulo(DatosTitulo dato) {
/* 252 */     switch (dato) {
/*     */       case RENTABILIDAD:
/* 254 */         return getUltimoCambioPrecio();
/*     */       case TIR:
/* 256 */         return ((Double)this.tir.getLast()).doubleValue();
/*     */       case DURACION:
/* 258 */         return ((Double)this.duracion.getLast()).doubleValue();
/*     */       case DURACION_MOD:
/* 260 */         return ((Double)this.duracionMod.getLast()).doubleValue();
/*     */       case null:
/* 262 */         return ((Double)this.convexidad.getLast()).doubleValue();
/*     */       case CUPON:
/* 264 */         return ((Double)this.cuponCorrido.getLast()).doubleValue();
/*     */       case MARGEN:
/* 266 */         return ((Double)this.margen.getLast()).doubleValue();
/*     */     } 
/* 268 */     throw new IllegalArgumentException("Tipo de dato no encontrado para titulos");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNew() {
/* 277 */     return (this.precioSucio.size() <= 1);
/*     */   }
/*     */   
/* 280 */   public static final String EOL = System.lineSeparator();
/*     */   
/*     */   public String toString() {
/* 283 */     return "Titulo valor con las siguientes caracteristicas:" + EOL + 
/* 284 */       "ISIN: " + this.isin + EOL + 
/* 285 */       "NEMO: " + this.nemo + EOL + 
/* 286 */       "ULTIMA ACTUALIZACION: " + getUltimaActualizacion() + EOL + 
/* 287 */       "PRECIO: " + this.precioSucio.getLast() + EOL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 296 */     int prime = 31;
/* 297 */     int result = 1;
/* 298 */     result = 31 * result + ((this.isin == null) ? 0 : this.isin.hashCode());
/* 299 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object that) {
/* 309 */     if (this == that)
/* 310 */       return true; 
/* 311 */     if (that == null)
/* 312 */       return false; 
/* 313 */     if (!(that instanceof Titulo))
/* 314 */       return false; 
/* 315 */     Titulo other = (Titulo)that;
/* 316 */     if (this.isin == null) {
/* 317 */       if (other.isin != null)
/* 318 */         return false; 
/* 319 */     } else if (!this.isin.equals(other.isin)) {
/* 320 */       return false;
/* 321 */     }  return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum DatosTitulo
/*     */   {
/* 329 */     RENTABILIDAD, TIR, DURACION, DURACION_MOD, CONVEXIDAD, CUPON, MARGEN;
/*     */   }
/*     */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\titulo\Titulo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */