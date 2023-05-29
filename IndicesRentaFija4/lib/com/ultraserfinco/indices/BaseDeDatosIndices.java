/*     */ package com.ultraserfinco.indices;
/*     */ 
/*     */ import com.ultraserfinco.titulo.BaseDeDatosTitulos;
/*     */ import com.ultraserfinco.titulo.EnumerableProperties;
/*     */ import com.ultraserfinco.titulo.Titulo;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.nio.file.CopyOption;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.StandardCopyOption;
/*     */ import java.sql.Date;
/*     */ import java.time.LocalDate;
/*     */ import java.time.temporal.ChronoUnit;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import javafx.scene.control.ProgressBar;
/*     */ import org.apache.poi.EncryptedDocumentException;
/*     */ import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
/*     */ import org.apache.poi.ss.usermodel.Row;
/*     */ import org.apache.poi.ss.usermodel.Sheet;
/*     */ import org.apache.poi.ss.usermodel.Workbook;
/*     */ import org.apache.poi.ss.usermodel.WorkbookFactory;
/*     */ import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
/*     */ public class BaseDeDatosIndices
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7140132173465608838L;
/*     */   private static final String ARCHIVO_MAP_INDICES = "MapIndices";
/*     */   private final List<Indice> listaIndices;
/*     */   
/*     */   private BaseDeDatosIndices() {
/*  62 */     this.listaIndices = new ArrayList<>();
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
/*     */   public static void generarIndices(LocalDate fechaInicio, LocalDate fechaFinal, File carpetaContenedora, List<String> nemos, List<Integer> gruposTasa, List<Integer> gruposCalif, List<Integer> clases, List<Integer> gruposDiasAlV, List<EnumerableProperties.Moneda> monedas, ProgressBar progressBar) {
/* 166 */     Queue<File> fileList = getFileList(fechaInicio.minusDays(1L), fechaFinal, carpetaContenedora);
/* 167 */     Map<String, Indice> mapNombreToIndice = getMapNombreToIndice();
/* 168 */     List<String> listaNombres = listaNombres(nemos, gruposTasa, gruposCalif, clases, gruposDiasAlV, monedas);
/*     */     
/* 170 */     List<Indice> indicesActualizados = new ArrayList<>();
/*     */     
/* 172 */     for (String nombre : listaNombres) {
/* 173 */       if (!mapNombreToIndice.containsKey(nombre)) {
/* 174 */         int indice = listaNombres.indexOf(nombre);
/* 175 */         mapNombreToIndice.put(nombre, Indice.getIndiceFromInfo(nemos.get(indice), ((Integer)gruposTasa.get(indice)).intValue(), ((Integer)gruposCalif.get(indice)).intValue(), (
/* 176 */               (Integer)clases.get(indice)).intValue(), ((Integer)gruposDiasAlV.get(indice)).intValue(), monedas.get(indice)));
/*     */       } 
/* 178 */       indicesActualizados.add(mapNombreToIndice.get(nombre));
/*     */     } 
/*     */     
/* 181 */     LocalDate fechaActual = fechaInicio;
/* 182 */     BaseDeDatosTitulos baseDeDatos = new BaseDeDatosTitulos(3);
/*     */     
/* 184 */     baseDeDatos.actualizarDatos(((File)fileList.remove()).getAbsolutePath(), ((File)fileList.remove()).getAbsolutePath(), fechaInicio.minusDays(1L));
/*     */     
/* 186 */     double progress = 0.0D;
/* 187 */     double deltaProgress = 1.0D / ChronoUnit.DAYS.between(fechaInicio, fechaFinal);
/*     */     
/* 189 */     while (!fechaActual.isAfter(fechaFinal)) {
/* 190 */       progress += deltaProgress;
/* 191 */       progressBar.setProgress(progress);
/*     */       
/* 193 */       List<Indice> indicesNoActualizados = new ArrayList<>();
/*     */ 
/*     */       
/* 196 */       for (Indice indice : indicesActualizados) {
/* 197 */         if (!indice.tieneValorEnFecha(fechaActual) || !indice.tieneValorEnFecha(fechaActual.plusDays(1L))) {
/* 198 */           indicesNoActualizados.add(indice);
/*     */         }
/*     */       } 
/*     */       
/* 202 */       if (indicesNoActualizados.isEmpty()) {
/* 203 */         fileList.remove();
/* 204 */         fileList.remove();
/* 205 */         fechaActual = fechaActual.plusDays(1L);
/*     */         
/*     */         continue;
/*     */       } 
/* 209 */       baseDeDatos.actualizarDatos(((File)fileList.remove()).getAbsolutePath(), ((File)fileList.remove()).getAbsolutePath(), fechaActual);
/* 210 */       for (Indice indice : indicesNoActualizados) {
/* 211 */         Set<Titulo> titulos = baseDeDatos.getTitulosFromInfo(indice.getNemo(), indice.getGrupoTasa(), 
/* 212 */             indice.getGrupoCalif(), indice.getClase(), indice.getGrupoDiasAlV(), indice.getMoneda());
/* 213 */         List<BigDecimal> valores = baseDeDatos.getValoresPromedioDeGrupo(titulos, 10, new Titulo.DatosTitulo[] {
/* 214 */               Titulo.DatosTitulo.RENTABILIDAD, Titulo.DatosTitulo.MARGEN, Titulo.DatosTitulo.TIR });
/* 215 */         indice.actualizarIndice(valores, fechaActual, new Titulo.DatosTitulo[] { Titulo.DatosTitulo.RENTABILIDAD, Titulo.DatosTitulo.MARGEN, Titulo.DatosTitulo.TIR });
/*     */       } 
/* 217 */       fechaActual = fechaActual.plusDays(1L);
/*     */     } 
/*     */     
/* 220 */     File archivoMapIndices = new File(String.valueOf(System.getProperty("user.dir")) + "/indices/" + "MapIndices");
/*     */ 
/*     */     
/* 223 */     if (archivoMapIndices.exists()) {
/*     */       try {
/* 225 */         Files.copy(archivoMapIndices.toPath(), Paths.get(String.valueOf(System.getProperty("user.dir")) + "/copia_de_seguridad/" + "MapIndices", new String[0]), new CopyOption[] {
/* 226 */               StandardCopyOption.REPLACE_EXISTING });
/* 227 */       } catch (IOException e) {
/* 228 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */     try {
/* 232 */       Exception exception2, exception1 = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 239 */     catch (IOException ex) {
/* 240 */       ex.printStackTrace();
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
/*     */   public static List<String> listaNombres(List<String> nemos, List<Integer> gruposTasa, List<Integer> gruposCalif, List<Integer> clases, List<Integer> gruposDiasAlV, List<EnumerableProperties.Moneda> monedas) {
/* 252 */     List<String> listaNombres = new ArrayList<>();
/* 253 */     for (int i = 0; i < gruposTasa.size(); i++) {
/* 254 */       listaNombres.add(Indice.getNombreIndiceFromInfo(nemos.get(i), ((Integer)gruposTasa.get(i)).intValue(), ((Integer)gruposCalif.get(i)).intValue(), (
/* 255 */             (Integer)clases.get(i)).intValue(), ((Integer)gruposDiasAlV.get(i)).intValue(), monedas.get(i)));
/*     */     }
/* 257 */     return listaNombres;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<String, Indice> getMapNombreToIndice() {
/* 267 */     File archivoMapIndices = new File(String.valueOf(System.getProperty("user.dir")) + "/indices/" + "MapIndices");
/* 268 */     Map<String, Indice> mapNameToIndex = null;
/* 269 */     boolean error = false;
/*     */     
/* 271 */     if (!archivoMapIndices.exists()) return mapNameToIndex = new HashMap<>();
/*     */ 
/*     */     
/* 274 */     if (error)
/*     */       try {
/* 276 */         Files.copy(archivoMapIndices.toPath(), Paths.get(String.valueOf(System.getProperty("user.dir")) + "/copia_de_seguridad/" + "MapIndices", new String[0]), new CopyOption[] {
/* 277 */               StandardCopyOption.REPLACE_EXISTING });
/* 278 */       } catch (IOException e) {
/* 279 */         e.printStackTrace();
/*     */       }  
/*     */     do {
/*     */       try {
/* 283 */         Exception exception2, exception1 = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 290 */       catch (IOException|ClassNotFoundException ex) {
/* 291 */         ex.printStackTrace();
/*     */ 
/*     */         
/*     */         try {
/* 295 */           Files.copy(Paths.get(String.valueOf(System.getProperty("user.dir")) + "/copia_de_seguridad/" + "MapIndices", new String[0]), archivoMapIndices.toPath(), new CopyOption[] {
/* 296 */                 StandardCopyOption.REPLACE_EXISTING });
/* 297 */           System.out.println("Intentando arreglar esta vuelta");
/* 298 */         } catch (IOException e) {
/* 299 */           e.printStackTrace();
/*     */         } finally {
/* 301 */           error = !error;
/*     */         } 
/*     */       } 
/* 304 */     } while (error);
/*     */     
/* 306 */     return mapNameToIndex;
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
/*     */   private static Queue<File> getFileList(LocalDate fechaInicio, LocalDate fechaFinal, File carpetaPrincipal) {
/* 320 */     File carpetaContenedora = new File(carpetaPrincipal.getAbsolutePath());
/* 321 */     Queue<File> archivos = new LinkedList<>();
/* 322 */     LocalDate fechaActual = fechaInicio;
/*     */ 
/*     */     
/* 325 */     while (fechaActual.getYear() < fechaFinal.getYear()) {
/* 326 */       archivos.addAll(getFilesOfYear(fechaActual, LocalDate.of(fechaActual.getYear(), 12, 31), carpetaContenedora));
/* 327 */       fechaActual = LocalDate.of(fechaActual.getYear() + 1, 1, 1);
/*     */     } 
/* 329 */     archivos.addAll(getFilesOfYear(fechaActual, fechaFinal, carpetaContenedora));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 334 */     return archivos;
/*     */   }
/*     */   
/*     */   private static Queue<File> getFilesOfYear(LocalDate fechaInicial, LocalDate fechaFinal, File carpetaAnos) {
/* 338 */     File carpetaContenedora = new File(carpetaAnos.getAbsolutePath());
/* 339 */     LocalDate fechaActual = fechaInicial;
/* 340 */     Queue<File> archivos = new LinkedList<>();
/* 341 */     File[] carpetaMeses = carpetaContenedora.listFiles((file, name) -> name.contains((new StringBuilder(String.valueOf(paramLocalDate.getYear()))).toString()));
/*     */     while (true) {
/* 343 */       int mes = fechaActual.getMonthValue();
/*     */       
/* 345 */       if (fechaActual.getMonthValue() < fechaFinal.getMonthValue()) {
/* 346 */         archivos.addAll(getFilesOfMonth(fechaActual, LocalDate.of(fechaActual.getYear(), mes, fechaActual.getMonth().length(fechaActual.isLeapYear())), carpetaMeses[0]));
/* 347 */         fechaActual = LocalDate.of(fechaActual.getYear(), mes + 1, 1); continue;
/*     */       }  break;
/* 349 */     }  archivos.addAll(getFilesOfMonth(fechaActual, fechaFinal, carpetaMeses[0]));
/*     */ 
/*     */ 
/*     */     
/* 353 */     return archivos;
/*     */   }
/*     */   
/*     */   private static Queue<File> getFilesOfMonth(LocalDate fechaInicial, LocalDate fechaFinal, File carpetaMeses) {
/* 357 */     File carpetaContenedora = new File(carpetaMeses.getAbsolutePath());
/* 358 */     LocalDate fechaActual = fechaInicial;
/* 359 */     Queue<File> archivos = new LinkedList<>();
/* 360 */     int mes = fechaActual.getMonthValue();
/* 361 */     File[] carpetaDias = carpetaContenedora.listFiles((file, name) -> !(!name.contains(String.format("%02d", new Object[] { Integer.valueOf(paramInt) })) && !name.toLowerCase().contains(Meses.getMesFromNumero(paramInt).toString().toLowerCase())));
/*     */     
/* 363 */     while (!fechaActual.isAfter(fechaFinal)) {
/* 364 */       int dia = fechaActual.getDayOfMonth();
/* 365 */       File[] carpetaDia = carpetaDias[0].listFiles((file, name) -> name.startsWith(String.format("%02d", new Object[] { Integer.valueOf(paramInt) })));
/* 366 */       System.out.println(carpetaDia[0]);
/*     */       
/* 368 */       File[] archivoSXDia = carpetaDia[0].listFiles((file, name) -> name.startsWith("SX"));
/* 369 */       if (archivoSXDia.length == 0) archivoSXDia = carpetaDia[0].listFiles((file, name) -> name.startsWith("SP")); 
/* 370 */       File[] archivoSMDia = carpetaDia[0].listFiles((file, name) -> name.startsWith("SM"));
/* 371 */       archivos.add(archivoSXDia[0]);
/* 372 */       archivos.add(archivoSMDia[0]);
/* 373 */       fechaActual = fechaActual.plusDays(1L);
/*     */     } 
/*     */     
/* 376 */     return archivos;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static void guardarIndicesEnExcel(String carpetaContenedora, String carpetaGuardar, LocalDate fechaInicial, LocalDate fechaFinal, ProgressBar progressBar) throws EncryptedDocumentException, InvalidFormatException, IOException {
/* 492 */     if (fechaFinal.isBefore(fechaInicial)) throw new IllegalArgumentException("Fecha inicial despues de fecha final.");
/*     */ 
/*     */ 
/*     */     
/* 496 */     Workbook queryWorkbook = null;
/*     */     
/* 498 */     File indicesPorCrear = new File(String.valueOf(System.getProperty("user.dir")) + "/indices/IndicesPorCrear.xlsx");
/* 499 */     InputStream inp = new FileInputStream(indicesPorCrear.getAbsolutePath());
/* 500 */     queryWorkbook = WorkbookFactory.create(inp);
/*     */ 
/*     */     
/* 503 */     Sheet mainSheet = queryWorkbook.getSheetAt(0);
/* 504 */     int numeroIndices = (int)mainSheet.getRow(0).getCell(7).getNumericCellValue();
/*     */     
/* 506 */     List<String> nemos = new ArrayList<>();
/* 507 */     List<Integer> gruposTasa = new ArrayList<>();
/* 508 */     List<Integer> gruposCalif = new ArrayList<>();
/* 509 */     List<Integer> clases = new ArrayList<>();
/* 510 */     List<Integer> gruposDiasAlV = new ArrayList<>();
/* 511 */     List<EnumerableProperties.Moneda> monedas = new ArrayList<>();
/*     */     
/* 513 */     for (int i = 1; i <= numeroIndices; i++) {
/* 514 */       Row fila = mainSheet.getRow(i);
/* 515 */       String nemo = fila.getCell(0).getStringCellValue();
/* 516 */       nemos.add(nemo);
/* 517 */       gruposTasa.add(Integer.valueOf((int)fila.getCell(1).getNumericCellValue()));
/* 518 */       gruposCalif.add(Integer.valueOf((int)fila.getCell(2).getNumericCellValue()));
/* 519 */       clases.add(Integer.valueOf((int)fila.getCell(3).getNumericCellValue()));
/* 520 */       gruposDiasAlV.add(Integer.valueOf((int)fila.getCell(4).getNumericCellValue()));
/* 521 */       monedas.add(EnumerableProperties.Moneda.valueOf(fila.getCell(5).getStringCellValue()));
/*     */     } 
/* 523 */     generarIndices(fechaInicial, fechaFinal, new File(carpetaContenedora), nemos, 
/* 524 */         gruposTasa, gruposCalif, clases, gruposDiasAlV, monedas, progressBar);
/* 525 */     Map<String, Indice> indices = getMapNombreToIndice();
/* 526 */     List<String> listaNombres = listaNombres(nemos, gruposTasa, gruposCalif, clases, gruposDiasAlV, monedas);
/* 527 */     List<Indice> indicesAGuardar = new ArrayList<>();
/* 528 */     for (String nombre : listaNombres) {
/* 529 */       indicesAGuardar.add(indices.get(nombre));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 535 */     XSSFWorkbook xSSFWorkbook = new XSSFWorkbook();
/* 536 */     Sheet sheet = xSSFWorkbook.createSheet("Indices");
/* 537 */     LocalDate fechaActual = fechaInicial;
/* 538 */     int numeroFechas = 1;
/*     */     
/* 540 */     while (!fechaActual.isAfter(fechaFinal)) {
/* 541 */       Row fila = sheet.createRow(numeroFechas + 1);
/* 542 */       fila.createCell(0).setCellValue(Date.valueOf(fechaActual));
/* 543 */       fechaActual = fechaActual.plusDays(1L);
/* 544 */       numeroFechas++;
/*     */     } 
/*     */     
/* 547 */     Row filaNombre = sheet.createRow(0);
/* 548 */     Row filaLabels = sheet.createRow(1);
/* 549 */     filaLabels.createCell(0).setCellValue("TIPO DATO");
/* 550 */     int contadorIndices = 1;
/*     */     
/* 552 */     for (Indice indice : indicesAGuardar) {
/* 553 */       filaNombre.createCell(contadorIndices).setCellValue(indice.getNombre());
/*     */       
/* 555 */       Map<Titulo.DatosTitulo, List<BigDecimal>> indexData = indice.getValoresEntreFechas(fechaInicial, fechaFinal, new Titulo.DatosTitulo[] { Titulo.DatosTitulo.RENTABILIDAD, 
/* 556 */             Titulo.DatosTitulo.MARGEN, Titulo.DatosTitulo.TIR });
/*     */ 
/*     */       
/* 559 */       int contadorDatos = 0;
/* 560 */       for (Titulo.DatosTitulo dato : indexData.keySet()) {
/* 561 */         filaLabels.createCell(contadorIndices + contadorDatos).setCellValue(dato.toString());
/* 562 */         contadorDatos++;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 570 */       for (int j = 2; j < numeroFechas + 1; j++) {
/* 571 */         Row filaDatos = sheet.getRow(j);
/* 572 */         contadorDatos = 0;
/* 573 */         for (Titulo.DatosTitulo dato : indexData.keySet()) {
/* 574 */           filaDatos.createCell(contadorIndices + contadorDatos).setCellValue(((BigDecimal)((List<BigDecimal>)indexData.get(dato)).get(j - 2)).doubleValue());
/* 575 */           contadorDatos++;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 580 */       contadorIndices += indexData.size();
/*     */     } 
/*     */     
/* 583 */     File archivoGuardar = new File(String.valueOf(carpetaGuardar) + "/indices.xlsx");
/*     */     
/* 585 */     FileOutputStream fileOut = new FileOutputStream(archivoGuardar);
/* 586 */     xSSFWorkbook.write(fileOut);
/* 587 */     fileOut.close();
/* 588 */     xSSFWorkbook.close();
/*     */   }
/*     */ 
/*     */   
/*     */   private enum Meses
/*     */   {
/* 594 */     ENERO(1), FEBRERO(2), MARZO(3), ABRIL(4), MAYO(5), JUNIO(6),
/* 595 */     JULIO(7), AGOSTO(8), SEPTIEMBRE(9), OCTUBRE(10), NOVIEMBRE(11),
/* 596 */     DICIEMBRE(12);
/*     */     public final int numero;
/*     */     
/* 599 */     Meses(int numero) { this.numero = numero; } public static Meses getMesFromNumero(int numero) { byte b;
/*     */       int i;
/*     */       Meses[] arrayOfMeses;
/* 602 */       for (i = (arrayOfMeses = values()).length, b = 0; b < i; ) { Meses mes = arrayOfMeses[b];
/* 603 */         if (mes.numero == numero)
/* 604 */           return mes; 
/*     */         b++; }
/*     */       
/* 607 */       throw new IllegalArgumentException("No existe mes con numero " + numero); }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\indices\BaseDeDatosIndices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */