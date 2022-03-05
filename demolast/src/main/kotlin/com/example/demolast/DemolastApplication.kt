package com.example.demolast

import com.example.demolast.dao.CategoryRepository
import com.example.demolast.dao.ProductRepository
import com.example.demolast.data.Category
import com.example.demolast.data.Product
import net.bytebuddy.utility.RandomString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import java.util.*

@SpringBootApplication
abstract class DemolastApplication : CommandLineRunner {

	@Autowired
	lateinit var productRepository: ProductRepository;

	@Autowired
	lateinit var categoryRepository: CategoryRepository;

	@Autowired
	private val repositoryRestConfiguration: RepositoryRestConfiguration? = null

	override fun run(vararg args: String?) {
		val c1 = Category()
		c1.id = null;
		c1.name = "Computers";
		c1.description = null;
		c1.products = null;
		val c2 = Category()
		c2.id = null;
		c2.name = "Printers";
		c2.description = null;
		c2.products = null;
		val c3 = Category()
		c3.id = null;
		c3.name = "Smartphones";
		c3.description = null;
		c3.products = null;
		categoryRepository!!.save(c1)
		categoryRepository!!.save(c2)
		categoryRepository!!.save(c3)

		val rnd = Random()
		categoryRepository!!.findAll().forEach { category ->
			for (i in 0..9) {
				val p = Product()
				p.name = (RandomString.make(10))
				p.currentPrice = ((100 + rnd.nextInt(10000)).toDouble())
				p.available = (rnd.nextBoolean())
				p.promotion = (rnd.nextBoolean())
				p.selected = (rnd.nextBoolean())
				p.category = (category)
				p.photoName = ("unknown.png")
				productRepository!!.save(p)
			}
		}
	}
}

	fun main(args: Array<String>) {
		runApplication<DemolastApplication>(*args)

	}



