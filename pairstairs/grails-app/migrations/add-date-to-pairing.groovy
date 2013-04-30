databaseChangeLog = {

    changeSet(author: "ben", id: "add-pairingDate-to-pairing") {

        addColumn(tableName: "pairing") {
            column(name: "pairingDate", type: "timestamp")
        }

        grailsChange {
            change {
                sql.execute("UPDATE pairing SET pairingDate = NOW()")
            }
            rollback {
            }
        }

        addNotNullConstraint(tableName: "pairing", columnName: "pairingDate")
    }
}