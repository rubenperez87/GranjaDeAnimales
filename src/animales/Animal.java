/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animales;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Clase abstracta que representa un animal de la granja. 
 * Contiene atributos y
 * comportamientos comunes a todos los animales.
 *
 * @author Gonzalo Fernández
 */
public abstract class Animal {

    protected String codigo;
    private LocalDate fechaNacimiento;
    private char sexo;
    private double peso;

    /**
    * Crea un nuevo objeto Animal con un código identificativo, fecha de
    * nacimiento, sexo y peso.
    *
    * Realiza validaciones sobre los parámetros:
    * - El código debe tener exactamente 5 caracteres que sean dígitos (0-9) o
    * letras minúsculas (a-z).
    * - El sexo debe ser 'M' (hembra) o 'H' (macho).
    * - El peso debe ser un valor positivo mayor que cero.
    * - La fecha de nacimiento debe estar en formato ISO-8601 (yyyy-MM-dd) válido.
    *
    * Si algún parámetro no cumple estas condiciones, se lanza una
    * IllegalArgumentException.
    *
    * @param codigo el código identificativo del animal, compuesto por 5 caracteres
    * alfanuméricos en minúscula
    * @param fechaNacimiento la fecha de nacimiento en formato "yyyy-MM-dd"
    * @param sexo  el sexo del animal, 'M' para hembra o 'H' para macho
    * @param peso peso del animal en kilogramos,debe ser mayor que 0
    * @throws IllegalArgumentException si el código no cumple el patrón, el sexo es
    * incorrecto, el peso no es positivo o la fecha no tiene un formato válido
    */
    
    public Animal(String codigo, String fechaNacimiento, char sexo, double peso) {

        LocalDate fecha;

        if (!codigo.matches("[0-9a-z]{5}") || (sexo != 'M' && sexo != 'H') || (peso <= 0)) {
            throw new IllegalArgumentException();
        } else {

            try {
                fecha = LocalDate.parse(fechaNacimiento);
            } catch (DateTimeParseException ex) {
                throw new IllegalArgumentException();
            }
            this.codigo = codigo;
            this.fechaNacimiento = fecha;
            this.sexo = sexo;
            this.peso = peso;
        }
    }
    
    /**
    * Devuelve el código identificativo del animal.
    *
    * @return el código del animal
    */
    public String getCodigo() {
        return codigo;
    }

    /**
    * Establece el código identificativo del animal.
    *
    * El código debe estar formado por exactamente 5 caracteres
    * alfanuméricos en minúscula.
    *
    * @param codigo nuevo código identificativo
    * @throws IllegalArgumentException si el código no cumple el patrón
    */
    public void setCodigo(String codigo) {
        if (!codigo.matches("[0-9a-z]{5}")) {
            throw new IllegalArgumentException();
        } else {
            this.codigo = codigo;
        }
    }
    
    /**
    * Devuelve la fecha de nacimiento del animal.
    *
    * @return la fecha de nacimiento
    */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    /**
     * Establece la fecha de nacimiento del animal.
     *
     * La fecha debe estar en formato ISO-8601 (yyyy-MM-dd).
     *
     * @param fechaNacimiento fecha de nacimiento en formato "yyyy-MM-dd"
     * @throws IllegalArgumentException si la fecha no tiene un formato válido
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        LocalDate fecha;

        try {
            fecha = LocalDate.parse(fechaNacimiento);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException();
        }

        this.fechaNacimiento = fecha;
    }
    
    /**
     * Devuelve el sexo del animal.
     *
     * @return el sexo del animal ('M' hembra o 'H' macho)
     */
    public char getSexo() {
        return sexo;
    }
    
    /**
     * Establece el sexo del animal.
     *
     * @param sexo sexo del animal ('M' hembra o 'H' macho)
     * @throws IllegalArgumentException si el sexo no es 'M' ni 'H'
     */
    public void setSexo(char sexo) {
        if ((sexo != 'M' && sexo != 'H')) {
            throw new IllegalArgumentException();
        } else {
            this.sexo = sexo;
        }
    }
    
    /**
    * Devuelve el peso del animal en kilogramos.
    *
    * @return el peso del animal
    */
    public double getPeso() {
        return peso;
    }
    
    /**
    * Establece el peso del animal.
    *
    * @param peso peso del animal en kilogramos
    * @throws IllegalArgumentException si el peso es menor o igual que cero
    */
    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.peso = peso;
        }
    }

    /**
     * Calcula el código hash del objeto Animal.
     *
     * @return valor hash del objeto
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.codigo);
        hash = 19 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 19 * hash + this.sexo;
        hash = 19 * hash + (int) (Double.doubleToLongBits(this.peso) ^ (Double.doubleToLongBits(this.peso) >>> 32));
        return hash;
    }
    
    /**
     * Compara este objeto con otro para comprobar si son iguales.
     *
     * Dos animales se consideran iguales si tienen el mismo código, fecha de
     * nacimiento, sexo y peso.
     *
     * @param obj objeto con el que se compara
     * @return true si los objetos son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        if (this.sexo != other.sexo) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve una representación en forma de texto del objeto Animal.
     *
     * @return cadena con los datos del animal
     */
    @Override
    public String toString() {
        return "Animal{" + "codigo=" + codigo + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo + ", peso=" + peso + '}';
    }

    /**
     * Devuelve el sonido característico del animal.
     *
     * @return sonido del animal
     */
    public abstract String hacerSonido();

    /**
     * Describe cómo se comporta el animal cuando está contento.
     *
     * @return comportamiento del animal cuando se alegra
     */
    public abstract String alegrarse();

    /**
     * Describe cómo se comporta el animal cuando está enfadado.
     *
     * @return comportamiento del animal cuando se enfada
     */
    public abstract String enfadarse();

    /**
     * Devuelve una descripción del tipo de animal.
     *
     * @return tipo o especie del animal
     */
    public abstract String queSoy();

}
