/*     */ package com.ultraserfinco.indices;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.time.LocalDate;
/*     */ import java.time.temporal.ChronoUnit;
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
/*     */ public final class IntervaloFechas
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5932744008138562454L;
/*     */   private final LocalDate fechaInicial;
/*     */   private final LocalDate fechaFinal;
/*     */   
/*     */   public IntervaloFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
/*  37 */     if (fechaInicial.isAfter(fechaFinal)) throw new IllegalArgumentException("La fecha inicial no puede ser posterior a la final"); 
/*  38 */     this.fechaInicial = fechaInicial;
/*  39 */     this.fechaFinal = fechaFinal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contieneFecha(LocalDate fecha) {
/*  48 */     return (!fecha.isAfter(this.fechaFinal) && !fecha.isBefore(this.fechaInicial));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean contieneIntervalo(IntervaloFechas intervalo) {
/*  57 */     return (contieneFecha(intervalo.getFechaFinal()) && contieneFecha(intervalo.getFechaInicial()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntervaloFechas intersectarCon(IntervaloFechas intervalo) {
/*  67 */     if (contieneIntervalo(intervalo)) return intervalo; 
/*  68 */     if (intervalo.contieneIntervalo(this)) return this; 
/*  69 */     if (contieneFecha(intervalo.getFechaFinal())) return new IntervaloFechas(this.fechaInicial, intervalo.getFechaFinal()); 
/*  70 */     if (contieneFecha(intervalo.getFechaInicial())) return new IntervaloFechas(intervalo.getFechaInicial(), this.fechaFinal); 
/*  71 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalDate getFechaInicial() {
/*  78 */     return this.fechaInicial;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalDate getFechaFinal() {
/*  85 */     return this.fechaFinal;
/*     */   }
/*     */   
/*     */   public int getNumeroDias() {
/*  89 */     return (int)(ChronoUnit.DAYS.between(this.fechaInicial, this.fechaFinal) + 1L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  97 */     int prime = 31;
/*  98 */     int result = 1;
/*  99 */     result = 31 * result + ((this.fechaFinal == null) ? 0 : this.fechaFinal.hashCode());
/* 100 */     result = 31 * result + ((this.fechaInicial == null) ? 0 : this.fechaInicial.hashCode());
/* 101 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 109 */     if (obj instanceof IntervaloFechas) {
/* 110 */       return (((IntervaloFechas)obj).getFechaInicial().equals(this.fechaInicial) && (
/* 111 */         (IntervaloFechas)obj).getFechaFinal().equals(this.fechaFinal));
/*     */     }
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 122 */     return "[" + this.fechaInicial.toString() + ", " + this.fechaFinal.toString() + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\indices\IntervaloFechas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */