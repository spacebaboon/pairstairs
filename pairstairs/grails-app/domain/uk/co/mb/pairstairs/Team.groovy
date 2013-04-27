package uk.co.mb.pairstairs

class Team {

    String name

    static hasMany = [coders: Coder]

    static constraints = {
        name(blank: false, unique: true)
    }

    String toString() {
        name
    }

}
