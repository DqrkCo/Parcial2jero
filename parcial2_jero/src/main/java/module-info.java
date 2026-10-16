module co.edu.poli.parcial2_jero {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires io.github.cdimascio.dotenv.java;
	

    opens co.edu.poli.parcial2_jero.vista to javafx.fxml, javafx.graphics;
    opens co.edu.poli.parcial2_jero.controlador to javafx.fxml;
    exports co.edu.poli.parcial2_jero.controlador;
    
}
