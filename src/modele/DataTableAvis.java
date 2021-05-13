package modele;

import java.util.List;

import modele.*;

public class DataTableAvis {
 
 int  iTotalRecords;
  
 int  iTotalDisplayRecords;
  
 String  sEcho;
  
 String sColumns;
  
 List<HAS_LiensPageCT_bdpm> data;
 
 
 
 public int getiTotalRecords() {
  return iTotalRecords;
 }
 
 public void setiTotalRecords(int iTotalRecords) {
  this.iTotalRecords = iTotalRecords;
 }
 
 public int getiTotalDisplayRecords() {
  return iTotalDisplayRecords;
 }
 
 public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
  this.iTotalDisplayRecords = iTotalDisplayRecords;
 }
 
 public String getsEcho() {
  return sEcho;
 }
 
 public void setsEcho(String sEcho) {
  this.sEcho = sEcho;
 }
 
 public String getsColumns() {
  return sColumns;
 }
 
 public void setsColumns(String sColumns) {
  this.sColumns = sColumns;
 }
 
 public List<HAS_LiensPageCT_bdpm> getInfoData() {
  return data;
 }
 
 public void setCompoData(List<HAS_LiensPageCT_bdpm> infoData) {
  this.data = infoData;
 }
}
