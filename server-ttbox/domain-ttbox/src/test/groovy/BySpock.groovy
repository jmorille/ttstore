import static org.junit.Assert.*;


class BySpock {

	static void main(def args){
		def mylist=[1,2,"Lars","4"]
		mylist.each{ println it }
	}
	
	def "Bye spock"() {
		
	}
}
