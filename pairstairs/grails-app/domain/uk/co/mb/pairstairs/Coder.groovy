package uk.co.mb.pairstairs

class Coder {

    String name
    String avatarUrl

    static constraints = {
        name(blank: false, unique: true)
        avatarUrl(url: true, nullable: true)
    }

    String toString() {
        name
    }

}
