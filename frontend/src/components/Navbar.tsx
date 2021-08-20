import { HiSearch } from "react-icons/hi"

function Navbar() {
    return (
        <nav className="bg-white">
            {/* left */}
            <div className="flex-items-center">
                <h1>Logo</h1>
                <div className="flex ml-2 items-center rounded-full">
                    <HiSearch className="h-6"/>
                    <input type="text" placeholder="Search Wave" />
                </div>  
            </div>


            {/* center */}


            {/* right */}
        </nav>
    )
}

export default Navbar
