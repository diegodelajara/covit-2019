export default {
    setEntry(state, newEntry) {
        state.entries = newEntry
    },
    setTitleRowToEdit(state, title) {
        state.selectedRow.title = title
    }
}