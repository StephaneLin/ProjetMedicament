package modele;

import java.util.List;

import modele.*;

public class DataTableMedoc {
 
 int  iTotalRecords;
  
 int  iTotalDisplayRecords;
  
 String  sEcho;
  
 String sColumns;
  
 List<CIS_bdpm> data;
 
 
 
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
 
 public List<CIS_bdpm> getmedocData() {
  return data;
 }
 
 public void setmedocData(List<CIS_bdpm> medocData) {
  this.data = medocData;
 }
}
