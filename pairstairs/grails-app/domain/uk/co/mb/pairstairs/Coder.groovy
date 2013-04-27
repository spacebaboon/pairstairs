package uk.co.mb.pairstairs

class Coder {

    String name

    static constraints = {
        name(blank: false, unique: true)
    }

    String toString() {
        name
    }

}
