package uk.co.mb.pairstairs

class Team {

    String name
    Stairs stairs

    Team(){
        stairs = new Stairs()
    }

    static hasMany = [coders: Coder]

    static constraints = {
        name(blank: false, unique: true)
    }

    String toString() {
        name
    }

}
