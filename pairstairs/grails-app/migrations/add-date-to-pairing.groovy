databaseChangeLog = {

    changeSet(author: "ben", id: "add-date-to_pairing") {
        addColumn(tableName: "pairing") {
            column(name: "date", type: "timestamp")
        }

        grailsChange {
            change {
                sql.execute("UPDATE pairing SET date = NOW()")
            }
            rollback {
            }
        }

        addNotNullConstraint(tableName: "paairing", columnName: "date")
    }
}