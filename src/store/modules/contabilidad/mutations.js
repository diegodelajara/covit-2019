export default {
  setEntries (state, list) {
    state.entries = list
  },
  setEntry(state, newEntry) {
    state.entries.push(newEntry)
  }
}
