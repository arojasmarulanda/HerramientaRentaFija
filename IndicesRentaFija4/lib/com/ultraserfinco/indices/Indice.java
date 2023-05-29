/*     */ package com.ultraserfinco.indices;
/*     */ 
/*     */ import com.ultraserfinco.titulo.EnumerableProperties;
/*     */ import com.ultraserfinco.titulo.SMInfoValmerFormat;
/*     */ import com.ultraserfinco.titulo.Titulo;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.time.LocalDate;
/*     */ import java.time.temporal.ChronoUnit;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
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
/*     */ public class Indice
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1453208985234992445L;
/*     */   private final String nombre;
/*     */   private final List<IntervaloFechas> intervalosFecha;
/*     */   private final Map<Titulo.DatosTitulo, LinkedList<BigDecimal>> indiceData;
/*     */   private final int grupoTasa;
/*     */   private final int grupoCalif;
/*     */   private final int clase;
/*     */   private final int grupoDiasAlV;
/*     */   private final String nemo;
/*     */   private final EnumerableProperties.Moneda moneda;
/*     */   
/*     */   protected Indice(String nombre, String nemo, int grupoTasa, int grupoCalif, int clase, int grupoDiasAlV, EnumerableProperties.Moneda moneda) {
/*  51 */     this.nombre = nombre;
/*  52 */     this.nemo = nemo;
/*  53 */     this.intervalosFecha = new LinkedList<>();
/*  54 */     this.indiceData = new LinkedHashMap<>();
/*  55 */     this.grupoTasa = grupoTasa;
/*  56 */     this.grupoCalif = grupoCalif;
/*  57 */     this.clase = clase;
/*  58 */     this.grupoDiasAlV = grupoDiasAlV;
/*  59 */     this.moneda = moneda;
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
/*     */   public static Indice getIndiceFromInfo(String nemo, int tasa, int grupoCalif, int clase, int grupoDiasAlV, EnumerableProperties.Moneda moneda) {
/*  74 */     String nombre = getNombreIndiceFromInfo(nemo, tasa, grupoCalif, clase, grupoDiasAlV, moneda);
/*  75 */     return new Indice(nombre, nemo, tasa, grupoCalif, clase, grupoDiasAlV, moneda);
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
/*     */   public static String getNombreIndiceFromInfo(String nemo, int tasa, int grupoCalif, int clase, int grupoDiasAlV, EnumerableProperties.Moneda moneda) {
/*  87 */     String filteredNemo = nemo.startsWith("NO_") ? "" : nemo;
/*  88 */     String nameMoneda = moneda.equals(EnumerableProperties.Moneda.NO_APLICA) ? "" : moneda.toString();
/*  89 */     String nombre = String.valueOf(filteredNemo) + "_" + SMInfoValmerFormat.grupoTasaToString(tasa) + "_" + SMInfoValmerFormat.grupoClaseToString(clase) + "_" + 
/*  90 */       SMInfoValmerFormat.grupoCalifToString(grupoCalif) + "_" + nameMoneda + "_" + SMInfoValmerFormat.grupoDiasAlVenToString(grupoDiasAlV);
/*  91 */     nombre = nombre.replace("__", "_").replace("__", "_").replace("__", "_");
/*  92 */     if (nombre.startsWith("_")) nombre = nombre.substring(1); 
/*  93 */     if (nombre.endsWith("_")) nombre = nombre.substring(0, nombre.length() - 1); 
/*  94 */     return nombre;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actualizarIndice(List<BigDecimal> values, LocalDate fecha, Titulo.DatosTitulo... datos) {
/* 105 */     if (tieneValorEnFecha(fecha))
/*     */       return; 
/* 107 */     if (this.intervalosFecha.isEmpty()) {
/* 108 */       for (int i = 0; i < datos.length; i++) {
/* 109 */         if (!this.indiceData.containsKey(datos[i])) {
/* 110 */           this.indiceData.put(datos[i], new LinkedList<>());
/*     */         }
/* 112 */         List<BigDecimal> dataList = this.indiceData.get(datos[i]);
/* 113 */         dataList.add(values.get(i));
/*     */       } 
/* 115 */       this.intervalosFecha.add(new IntervaloFechas(fecha, fecha));
/*     */       
/*     */       return;
/*     */     } 
/* 119 */     if (fecha.isBefore(((IntervaloFechas)this.intervalosFecha.get(0)).getFechaInicial())) {
/* 120 */       for (int i = 0; i < datos.length; i++) {
/* 121 */         if (!this.indiceData.containsKey(datos[i])) {
/* 122 */           this.indiceData.put(datos[i], new LinkedList<>());
/*     */         }
/* 124 */         List<BigDecimal> dataList = this.indiceData.get(datos[i]);
/* 125 */         dataList.add(0, values.get(i));
/*     */       } 
/* 127 */       this.intervalosFecha.add(0, new IntervaloFechas(fecha, fecha));
/* 128 */       simplificar();
/*     */       return;
/*     */     } 
/* 131 */     if (fecha.isAfter(((IntervaloFechas)this.intervalosFecha.get(this.intervalosFecha.size() - 1)).getFechaFinal())) {
/* 132 */       for (int i = 0; i < datos.length; i++) {
/* 133 */         if (!this.indiceData.containsKey(datos[i])) {
/* 134 */           this.indiceData.put(datos[i], new LinkedList<>());
/*     */         }
/* 136 */         List<BigDecimal> dataList = this.indiceData.get(datos[i]);
/* 137 */         dataList.add(values.get(i));
/*     */       } 
/* 139 */       this.intervalosFecha.add(new IntervaloFechas(fecha, fecha));
/* 140 */       simplificar();
/*     */       return;
/*     */     } 
/* 143 */     int intervalCounter = 0;
/* 144 */     for (int index = 0; index < this.intervalosFecha.size() - 1; index++) {
/* 145 */       intervalCounter += ((IntervaloFechas)this.intervalosFecha.get(index)).getNumeroDias();
/* 146 */       if (fecha.isAfter(((IntervaloFechas)this.intervalosFecha.get(index)).getFechaFinal()) && 
/* 147 */         fecha.isBefore(((IntervaloFechas)this.intervalosFecha.get(index + 1)).getFechaInicial())) {
/* 148 */         this.intervalosFecha.add(index + 1, new IntervaloFechas(fecha, fecha));
/* 149 */         for (int i = 0; i < datos.length; i++) {
/* 150 */           if (!this.indiceData.containsKey(datos[i])) {
/* 151 */             this.indiceData.put(datos[i], new LinkedList<>());
/*     */           }
/* 153 */           List<BigDecimal> dataList = this.indiceData.get(datos[i]);
/* 154 */           dataList.add(intervalCounter, values.get(i));
/*     */         } 
/* 156 */         simplificar();
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void simplificar() {
/* 169 */     ListIterator<IntervaloFechas> iter = this.intervalosFecha.listIterator();
/* 170 */     IntervaloFechas last = iter.next();
/* 171 */     while (iter.hasNext()) {
/* 172 */       IntervaloFechas next = iter.next();
/* 173 */       if (last.getFechaFinal().plusDays(1L).equals(next.getFechaInicial())) {
/* 174 */         iter.remove();
/* 175 */         iter.previous();
/* 176 */         iter.remove();
/* 177 */         iter.add(new IntervaloFechas(last.getFechaInicial(), next.getFechaFinal()));
/* 178 */         last = iter.previous(); continue;
/*     */       } 
/* 180 */       last = next;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNombre() {
/* 189 */     return this.nombre;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGrupoTasa() {
/* 196 */     return this.grupoTasa;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGrupoCalif() {
/* 203 */     return this.grupoCalif;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getClase() {
/* 210 */     return this.clase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGrupoDiasAlV() {
/* 217 */     return this.grupoDiasAlV;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumerableProperties.Moneda getMoneda() {
/* 224 */     return this.moneda;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNemo() {
/* 231 */     return this.nemo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Titulo.DatosTitulo, LinkedList<BigDecimal>> getIndiceData() {
/* 238 */     return new LinkedHashMap<>(this.indiceData);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Titulo.DatosTitulo, List<BigDecimal>> getValoresEntreFechas(LocalDate fechaInicial, LocalDate fechaFinal, Titulo.DatosTitulo... datos) {
/* 243 */     int dias = (int)ChronoUnit.DAYS.between(fechaInicial, fechaFinal);
/* 244 */     int indiceInicial = getIndexOf(fechaInicial, fechaFinal);
/* 245 */     int indiceFinal = indiceInicial + dias;
/* 246 */     Map<Titulo.DatosTitulo, List<BigDecimal>> subMap = new LinkedHashMap<>(); byte b; int i;
/*     */     Titulo.DatosTitulo[] arrayOfDatosTitulo;
/* 248 */     for (i = (arrayOfDatosTitulo = datos).length, b = 0; b < i; ) { Titulo.DatosTitulo dato = arrayOfDatosTitulo[b];
/* 249 */       subMap.put(dato, ((LinkedList<BigDecimal>)this.indiceData.get(dato)).subList(indiceInicial, indiceFinal + 1)); b++; }
/*     */     
/* 251 */     return subMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<IntervaloFechas> getIntervalosFecha() {
/* 259 */     return new LinkedList<>(this.intervalosFecha);
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
/*     */   private int getIndexOf(LocalDate fechaInicial, LocalDate fechaFinal) {
/* 271 */     int index = 0;
/* 272 */     IntervaloFechas intervalo = new IntervaloFechas(fechaInicial, fechaFinal);
/* 273 */     for (IntervaloFechas intervaloIndice : this.intervalosFecha) {
/* 274 */       if (intervaloIndice.contieneIntervalo(intervalo)) {
/* 275 */         index += (int)ChronoUnit.DAYS.between(intervaloIndice.getFechaInicial(), intervalo.getFechaInicial());
/*     */         break;
/*     */       } 
/* 278 */       index += intervaloIndice.getNumeroDias();
/*     */     } 
/* 280 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tieneValorEnFecha(LocalDate fecha) {
/* 289 */     for (IntervaloFechas intervalo : this.intervalosFecha) {
/* 290 */       if (intervalo.contieneFecha(fecha)) {
/* 291 */         return true;
/*     */       }
/*     */     } 
/* 294 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\indices\Indice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */