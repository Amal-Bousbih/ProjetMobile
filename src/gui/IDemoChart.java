/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
/**
 *
 * @author amalb
 */
public interface IDemoChart {
    
    /** A constant for the name field in a list activity. */
  String NAME = "name";
  /** A constant for the description field in a list activity. */
  String DESC = "desc";

  /**
   * Returns the chart name.
   * 
   * @return the chart name
   */
  String getName();

  /**
   * Returns the chart description.
   * 
   * @return the chart description
   */
  String getDesc();

  /**
   * Executes the chart demo.
   * 
   * @return the built UI
   */
    Component execute();

    public String getChartTitle();
    
    public Component getChartModelEditor();
    
}
