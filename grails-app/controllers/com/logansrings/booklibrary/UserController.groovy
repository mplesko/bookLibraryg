package com.logansrings.booklibrary

import com.logansrings.booklibrary.util.Encrypting

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def defaultAction = "login"
	
    def index = {
		flash.message = "called from action:index"
        redirect(action: "login")
    }

	def login = {} // nothing to do so go to login.gsp
	
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

	def doLogin = {
//		System.out.println("in password " + params.password)
		def encryptedPassword = Encrypting.instance.encrypt(params.password)
//		System.out.println("encrypt password " + encryptedPassword)
		def userInstance = User.findByUserNameAndPassword(params.userName, encryptedPassword)
		if (userInstance) {
//			flash.message = "user " + userInstance.userName + " has logged in"
//			System.out.println("user " + userInstance.userName + " has logged in")
			session.userId = userInstance.id
			session.userName = userInstance.userName
			redirect(action: "mylibrary")
		} else {
			flash.message = "Unable to login: user not found"
			redirect(action: "login")
		}
	}

    def createAccount = {
		System.out.println("in createAccount for " + params.userName)
		def encryptedPassword = Encrypting.instance.encrypt(params.password)
		
		params.password = encryptedPassword
		
        def userInstance = new User(params)
        if (userInstance.save(flush: true)) {
//            flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
			System.out.println("user " + userInstance.userName + " has been created")
			session.userId = userInstance.id
            redirect(action: 'mylibrary')
        }
        else {
			flash.message = "Unable to create account"
            redirect(action: "login")
        }
    }
	
    def logout = {
		session.userId = null
		def userInstance = new User()
		redirect(uri: '/')
    }

    def mylibrary = {
        def userInstance = User.get(session.userId)
		[userInstance: userInstance]
    }

//    def create = {
//        def userInstance = new User()
//        userInstance.properties = params
//        return [userInstance: userInstance]
//    }

//	def save = {
//        def userInstance = new User(params)
//        if (userInstance.save(flush: true)) {
//            flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
//            redirect(action: "show", id: userInstance.id)
//        }
//        else {
//            render(view: "create", model: [userInstance: userInstance])
//        }
//    }

//    def show = {
//        def userInstance = User.get(params.id)
//        if (!userInstance) {
//            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
//            redirect(action: "list")
//        }
//        else {
//            [userInstance: userInstance]
//        }
//    }

    def edit = {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [userInstance: userInstance]
        }
    }
//
//    def update = {
//        def userInstance = User.get(params.id)
//        if (userInstance) {
//            if (params.version) {
//                def version = params.version.toLong()
//                if (userInstance.version > version) {
//                    
//                    userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
//                    render(view: "edit", model: [userInstance: userInstance])
//                    return
//                }
//            }
//            userInstance.properties = params
//            if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
//                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
//                redirect(action: "show", id: userInstance.id)
//            }
//            else {
//                render(view: "edit", model: [userInstance: userInstance])
//            }
//        }
//        else {
//            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
//            redirect(action: "list")
//        }
//    }

//    def delete = {
//        def userInstance = User.get(params.id)
//        if (userInstance) {
//            try {
//                userInstance.delete(flush: true)
//                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
//                redirect(action: "list")
//            }
//            catch (org.springframework.dao.DataIntegrityViolationException e) {
//                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
//                redirect(action: "show", id: params.id)
//            }
//        }
//        else {
//            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
//            redirect(action: "list")
//        }
//    }
}
