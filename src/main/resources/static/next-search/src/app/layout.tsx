import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import Mode from "./mode";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Spring-boot search with Ai",
  description: "Spring-boot serach with AI",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <nav className="navbar navbar-expand-lg navbar-light bg-light p-2">
          <div className="container-fluid">
            <a className="navbar-brand" href="#">
              Spring-boot AI search with elasticsearch <span className="badge rounded-pill bg-primary">Beta</span>
            </a>

            {/* <div className="form-check form-switch d-flex">
              <Mode />
            </div> */}
          </div>
        </nav>
        {children}
      </body>
    </html>
  );
}
