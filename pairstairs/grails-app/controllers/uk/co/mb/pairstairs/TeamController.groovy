package uk.co.mb.pairstairs

import org.joda.time.DateTime
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.springframework.dao.DataIntegrityViolationException

class TeamController {

    private DateTimeFormatter fmt = DateTimeFormat.forPattern("EEE d MMMM");

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def stairs() {
        [coders: Coder.list(sort: 'name', order: 'asc')]
    }

    private Set<Coder> findCoders(int col, int row) {
        def coders = Coder.list(sort: 'name', order: 'asc')
        def coder1 = Coder.findByName(coders[col].name)
        def coder2 = Coder.findByName(coders[row].name)
        return [coder1, coder2]
    }

    private Pairing findPairing(int col, int row) {
        return Pairing.list().find { it.coders.containsAll(findCoders(col, row)) }
    }

    def showPairingDate(int col, int row) {
        def pairing = findPairing(col, row)
        if (pairing) {
            render fmt.print(pairing.date)
        } else {
            render ''
        }
    }

    def showPairing(int col, int row) {

        def pairing = findPairing(col, row)
        if (pairing) {
            def when = (pairing.daysAgo() == 0) ? "Today" : fmt.print(pairing.date)
            render "<span class='show-age days-old-${pairing.daysAgo()}' title='${when}'>X</span>"
        } else {
            render ''
        }
    }

    def togglePairing(int col, int row) {

        def stairs = Team.first().stairs

        def pairing = findPairing(col, row)
        if (pairing) {
            stairs.removeFromPairings(pairing)
            pairing.delete(flush: true)
            render "removing ${pairing}"
        } else {
            def newPairing = new Pairing(coders: findCoders(col, row), date: new DateTime()).save()
            stairs.addToPairings(newPairing)
            render "pairing ${newPairing}"
        }
    }


    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [teamInstanceList: Team.list(params), teamInstanceTotal: Team.count()]
    }

    def create() {
        [teamInstance: new Team(params)]
    }

    def save() {
        def teamInstance = new Team(params)
        if (!teamInstance.save(flush: true)) {
            render(view: "create", model: [teamInstance: teamInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'team.label', default: 'Team'), teamInstance.id])
        redirect(action: "show", id: teamInstance.id)
    }

    def show(Long id) {
        def teamInstance = Team.get(id)
        if (!teamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "list")
            return
        }

        [teamInstance: teamInstance]
    }

    def edit(Long id) {
        def teamInstance = Team.get(id)
        if (!teamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "list")
            return
        }

        [teamInstance: teamInstance]
    }

    def update(Long id, Long version) {
        def teamInstance = Team.get(id)
        if (!teamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (teamInstance.version > version) {
                teamInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'team.label', default: 'Team')] as Object[],
                        "Another user has updated this Team while you were editing")
                render(view: "edit", model: [teamInstance: teamInstance])
                return
            }
        }

        teamInstance.properties = params

        if (!teamInstance.save(flush: true)) {
            render(view: "edit", model: [teamInstance: teamInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'team.label', default: 'Team'), teamInstance.id])
        redirect(action: "show", id: teamInstance.id)
    }

    def delete(Long id) {
        def teamInstance = Team.get(id)
        if (!teamInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "list")
            return
        }

        try {
            teamInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'team.label', default: 'Team'), id])
            redirect(action: "show", id: id)
        }
    }
}
