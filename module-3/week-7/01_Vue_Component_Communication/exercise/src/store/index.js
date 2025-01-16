import { createStore as _createStore } from 'vuex';

export function createStore() {
  return _createStore({
    state: {
      books: [
      {
        title: "Kafka on the Shore",
        author: "Haruki Murakami",
        read: false,
        isbn: "9781784877989"
      },
      {
        title: "The Girl With All the Gifts",
        author: "M.R. Carey",
        read: true,
        isbn: "9780356500157"
      },
      {
        title: "The Old Man and the Sea",
        author: "Ernest Hemingway",
        read: true,
        isbn: "9780684830490"
      },
      {
        title: "Le Petit Prince",
        author: "Antoine de Saint-Exupéry",
        read: false,
        isbn: "9783125971400"
      }
    ]},
    mutations: {toggleReadStatus(state, isbn) {
      const book = state.books.find(book => book.isbn === isbn);
      if (book) {
        book.read = !book.read;
      }
    },
    addBook(state, book) {
      state.books.push(book);
    }
    
    },
    actions: {},
    modules: {},
    strict: true
  })
}
