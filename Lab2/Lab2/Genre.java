package Lab2;

// ArrayList нужен для хранения данных
import java.util.ArrayList;

/* Класс жанра. Содержит название жанра и ссылку на надЖанр (как подЖанр, но наоборот) - ссылка может быть null.
При этом, в данной концепции у жанра может быть много поджанров, но не более одного наджанра. Имеет методы:
	Конструктор от названия и наджанра;
	Конструктор от названия (если нет наджанра);
	Получение названия жанра;
	Получение наджанра;
	Проверка, является ли данный жанр поджанром;
	Проверка двух жанров на равенство (по названию);
	Проверка, включен ли один жанр в другой по названию (является ли первый поджанром второго);
	Проверка, включен ли один жанр в другой;
	Преобразование к строке с выводом названия и всей цепочки наджанров
*/
public class Genre {
	private String name_;      // Название
	private Genre superGenre_; // Ссылка на наджанр
	// Конструктор от названия и наджанра
	public Genre(String name, Genre superGenre) {
		name_ = name;
		superGenre_ = superGenre;
	}
	// Конструктор от названия, когда данный жанр не является чьим-либо поджанром: наджанр будет null'ом
	public Genre(String name) {
		this(name, null);
	}
	// Геттер названия
	public String getName() {
		return name_;
	}
	// Геттер надЖанра
	public Genre getSuperGenre() {
		return superGenre_;
	}
	// Проверка, является ли поджанром (есть ли у данного жанра наджанр)
	public boolean isSubgenre() {
		return superGenre_ != null;
	}
	// Проверка на равенство между жанрами (по названию)
	public boolean equals(Genre other) {
		return name_.equals(other.name_);
	}
	// Проверка на то, что один жанр является поджанром другого, переданного по названию
	// Другими словами, если подниматься по цепочке наджанров данного жанра, найдем ли мы такое название жанра
	public boolean includedIn(String genreName) {
		Genre tmp = this; // Промежуточная переменная для подъема по цепи наджанров
		while (true) {
			if (tmp.getName().equals(genreName)) // Если нашелся такой жанр в цепочке, название которого совпадало бы с данным,
				return true;                     // то начальный жанр является поджанром данного
			if (tmp.superGenre_ == null)         // Если это не так и цепочка закончилась, то не является
				return false;
			tmp = tmp.superGenre_;               // Раз цепочка продолжается, то поднимаемся выше
		}
	}
	// Та же проверка, но принимающая жанр, а не строку - вызывает предыдущую от названия
	public boolean includedIn(Genre other) {
		if (other == null)
			return false;
		return includedIn(other.getName());
	}
	// Приведение к строке. Если есть наджанр, он будет в скобках после названия жанра.
	// Если у наджанра есть наджанр, то он тоже будет у того в скобках, и так далее.
	public String toString() {
		if (superGenre_ != null) {
			// Тут неявно вызывается рекурсия, когда пишется superGenre_ - это то же самое, что superGenre_.toString().
			return name_ + "(" + superGenre_ + ")";
		} else
			return name_;
	}
}
