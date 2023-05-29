/*     */ package com.ultraserfinco.main;
/*     */ 
/*     */ import com.ultraserfinco.indices.BaseDeDatosIndices;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.time.LocalDate;
/*     */ import javafx.application.Application;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.DatePicker;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.ProgressBar;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.stage.DirectoryChooser;
/*     */ import javafx.stage.Stage;
/*     */ import org.apache.poi.EncryptedDocumentException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MainGeneradorIndices
/*     */   extends Application
/*     */ {
/*     */   @FXML
/*     */   private TextField carpetaInfoVText;
/*     */   @FXML
/*     */   private Button carpetaInfoVButton;
/*     */   @FXML
/*     */   private TextField guardarEnText;
/*     */   @FXML
/*     */   private Button guardarEnButton;
/*     */   @FXML
/*     */   private DatePicker fechaInicialPicker;
/*     */   
/*     */   public void start(Stage primaryStage) throws IOException {
/*  46 */     this.mainStage = primaryStage;
/*  47 */     Parent root = FXMLLoader.<Parent>load(getClass().getResource("InterfazGrafica.fxml"));
/*  48 */     Scene scene = new Scene(root);
/*     */     
/*  50 */     this.mainStage.setTitle("Indices Renta Fija");
/*  51 */     this.mainStage.setResizable(false);
/*  52 */     this.mainStage.setScene(scene);
/*  53 */     this.mainStage.show(); } @FXML
/*     */   private DatePicker fechaFinalPicker; @FXML
/*     */   private Button generarButton; @FXML
/*     */   private Button ayudaButton; @FXML
/*     */   private ProgressBar progressBar; @FXML
/*     */   private Label progressLabel; private Stage mainStage; @FXML
/*  59 */   protected void clickCarpetaInfoV(ActionEvent event) { DirectoryChooser dirChooser = new DirectoryChooser();
/*  60 */     dirChooser.setTitle("Seleccionar carpeta");
/*  61 */     File infoVFolder = dirChooser.showDialog(this.mainStage);
/*  62 */     if (infoVFolder != null) this.carpetaInfoVText.setText(infoVFolder.getAbsolutePath());  }
/*     */ 
/*     */   
/*     */   @FXML
/*     */   protected void clickGuardarEn(ActionEvent event) {
/*  67 */     DirectoryChooser dirChooser = new DirectoryChooser();
/*  68 */     dirChooser.setTitle("Seleccionar carpeta");
/*  69 */     File infoVFolder = dirChooser.showDialog(this.mainStage);
/*  70 */     if (infoVFolder != null) this.guardarEnText.setText(infoVFolder.getAbsolutePath()); 
/*     */   }
/*     */   @FXML
/*     */   protected void clickGenerar(ActionEvent event) {
/*  74 */     final LocalDate fechaInicial = this.fechaInicialPicker.getValue();
/*  75 */     final LocalDate fechaFinal = this.fechaFinalPicker.getValue();
/*  76 */     final String carpetaContenedora = this.carpetaInfoVText.getText();
/*  77 */     final String guardarEn = this.guardarEnText.getText();
/*  78 */     this.progressBar.setProgress(0.0D);
/*  79 */     this.progressLabel.setText("Relajate home que estoy trabajando en los indices...");
/*     */     
/*  81 */     (new Thread() {
/*     */         public void run() {
/*     */           try {
/*  84 */             BaseDeDatosIndices.guardarIndicesEnExcel(carpetaContenedora, guardarEn, fechaInicial, fechaFinal, MainGeneradorIndices.this.progressBar);
/*  85 */             Thread.sleep(100L);
/*  86 */           } catch (EncryptedDocumentException|org.apache.poi.openxml4j.exceptions.InvalidFormatException|IOException|InterruptedException e) {
/*  87 */             e.printStackTrace();
/*     */           } finally {
/*  89 */             MainGeneradorIndices.this.generarButton.setDisable(false);
/*  90 */             Platform.runLater(() -> MainGeneradorIndices.this.progressLabel.setText("Ehhh, ¡Casi que no!"));
/*     */           } 
/*     */         }
/*  93 */       }).start();
/*  94 */     this.generarButton.setDisable(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @FXML
/*     */   protected void clickAyuda(ActionEvent event) {}
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 103 */     launch(args);
/*     */   }
/*     */ }


/* Location:              C:\Users\arojasm\Downloads\IndicesRentaFija3.jar!\co\\ultraserfinco\main\MainGeneradorIndices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */